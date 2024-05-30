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
    creador_id: number
}
