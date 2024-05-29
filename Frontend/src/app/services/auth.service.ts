import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment.development';
import { Login } from '../models/login';
import { Usuario } from '../models/usuario';
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = environment.API_URL;

  constructor(private http: HttpClient, private ruta: Router) {
  }

  login(login: Login) {
    return this.http.post<any>(this.url + "auth/login", login);
  }

  register(register: Usuario) {
    return this.http.post<any>(this.url + "auth/register", register);
  }

  loggedIn() {
    // Comprueba si estas logeado
    return !!localStorage.getItem('token');
  }

  logOut() {
    // Elimina el token de localStorage
    localStorage.removeItem('token');
    this.ruta.navigate(['/login']);
  }

  getToken() {
    // Obtiene el token del localStorage
    return localStorage.getItem('token');
  }

  getTokenDescodificado(): any {
    // Obtiene el token JWT del almacenamiento local
    const token = this.getToken();


    // Verifica si el token es nulo o vacío
    if (!token) {
      // Si el token no existe, devuelve null 
      return null;
    }

    // Decodifica el token JWT para obtener la información del usuario
    const decodedToken: any = jwt_decode(token);

    console.log(decodedToken)
    // Devuelve el token descodificado del usuario
    return decodedToken || null;
  }
}
