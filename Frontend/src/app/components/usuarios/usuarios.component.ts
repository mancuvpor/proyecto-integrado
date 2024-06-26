import { Component, Input } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { Usuario } from '../../models/usuario';
import { UsuariosService } from '../../services/usuarios.service';
import { DecimalPipe, UpperCasePipe } from '@angular/common';

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [RouterLink, DecimalPipe, UpperCasePipe],
  templateUrl: './usuarios.component.html',
  styleUrl: './usuarios.component.css'
})
export class UsuariosComponent {

  public listaUsuarios: Usuario[] = [];
  public usuario: Usuario;
  @Input()
  public idCreador: any;


  constructor(private usuarioService: UsuariosService, private ruta: Router) {
    this.usuario = <Usuario>{};
    this.getAllUsuarios();
  }

  getAllUsuarios() {
    this.usuarioService.getAllUsuarios().subscribe({
      next: res => { this.listaUsuarios = res; console.log(res) },
      error: error => console.log(error)
    });
  }

  borrarUsuario(usuarioBorrar: Usuario) {
    if (confirm("¿Seguro que desea eliminar el usuario: " + usuarioBorrar.username + "?")) {
      this.usuarioService.borrarUsuarios(usuarioBorrar.id).subscribe({
        next: res => { this.getAllUsuarios() },
        error: error => console.log(error)
      });
    }
  }
}
