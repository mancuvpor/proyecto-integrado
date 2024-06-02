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

  getEventosPersonales() {
    return this.http.get<any>(this.url + "eventos/personales");
  }

  crearEventos(evento: Evento) {
    console.log(evento)
    return this.http.post<any>(this.url + "eventos", evento);
  }

  getEventoPorId(id: number) {
    return this.http.get<any>(this.url + "eventos/" + id);
  }

  modificarEventos(evento: Evento, idEvento: number) {
    console.log(evento)
    return this.http.put<any>(this.url + "eventos/" + idEvento, evento);
  }

  borrarEventos(idEvento: number) {
    return this.http.delete<any>(this.url + "eventos/" + idEvento);
  }


}
