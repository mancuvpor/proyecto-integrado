import { Time } from "@angular/common";

export interface Evento {

    idEvento: number,
    titulo: string,
    fecha: Date,
    hora: Time,
    lugar: string,
    descripcion: string,
    invitados: string,
    precio: number,
    creador_id: number
}
