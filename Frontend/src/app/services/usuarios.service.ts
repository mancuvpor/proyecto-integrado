import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  private url = environment.API_URL;
  constructor(private http: HttpClient) { }

  getAllUsuarios() {
    return this.http.get<any>(this.url + "usuarios");
  }

  crearUsuarios(usuario: Usuario) {
    console.log("Estamos en crear usuarios");
    console.log(usuario)
    return this.http.post<any>(this.url + "usuarios", usuario);
  }

  getUsuarioPorId(id: number) {
    console.log("Estamos en obtener usuario por id");
    return this.http.get<any>(this.url + "usuarios/" + id);
  }

  modificarUsuarios(usuario: Usuario, idUsuario: number) {
    console.log("Estamos en modificar usuarios");
    console.log(usuario)
    return this.http.put<any>(this.url + "usuarios/" + idUsuario, usuario);
  }

  borrarUsuarios(idUsuario: number) {
    console.log("Estamos en borrar usuarios");
    return this.http.delete<any>(this.url + "usuarios/" + idUsuario);
  }
}
