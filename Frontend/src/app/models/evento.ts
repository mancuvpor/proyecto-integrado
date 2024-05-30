import { Time } from "@angular/common";

export interface Evento {

    idEvento: number,
    titulo: string,
    fecha: string,
    hora: Time,
    lugar: string,
    descripcion: string,
    invitados: string,
    precio: number,
    telefono: string,
    creador_id: number
}
