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

  borrarEventos(idEvento: number) {
    console.log("Estamos en borrar eventos");
    return this.http.delete<any>(this.url + "eventos/" + idEvento);
  }
}
