import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  private url = environment.API_URL;
  constructor(private http: HttpClient) { }

  // getAllUsuarios(){
  //   return this.http.get<any>(this.url + "eventos");
  // }
}
