import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Evento } from '../models/evento';

@Injectable({
  providedIn: 'root'
})
export class EventosService {

  private url = environment.API_URL;

  constructor(private http: HttpClient) { }

  getAllEventos() {
    return this.http.get<any>(this.url + "eventos");
  }

  crearEventos(evento: Evento) {
    console.log("Estamos en crear eventos");
    return this.http.post<any>(this.url + "eventos", evento);
  }

  getEventoPorId(id: number) {
    console.log("Estamos en obtener evento por id");
    return this.http.get<any>(this.url + "eventos/" + id);
  }

  modificarEventos(evento: Evento, idEvento: number) {
    console.log("Estamos en modificar eventos");
    console.log(evento)
    return this.http.put<any>(this.url + "eventos/" + idEvento, evento);
  }

  borrarEventos(idEvento: number) {
    console.log("Estamos en borrar eventos");
    return this.http.delete<any>(this.url + "eventos/" + idEvento);
  }


}
