export const environment = {


    API_URL: "http://localhost:8081/api/v0/",


    jsonEquals: function (a: any, b: any) {
        return JSON.stringify(a) == JSON.stringify(b);
    },

    SeleccionarObj: function (lista: object[], obj: object) {
        var res;
        lista.forEach(valor => {
            if (environment.jsonEquals(valor, obj))
                res = valor;
        }
        );
    },

    //Funcion que nos sirve para que se queden marcados los elementos que ya esten seleccionados en un objeto
    SeleccionarObjArray: function (lista: Array<object>, objE: Array<object>) {
        var res = new Array();
        objE.forEach(ele => {
            lista.forEach(valor => {
                if (environment.jsonEquals(valor, ele))
                    res.push(valor);
            })
        })
        return res;
    }
};
