import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { UsuariosService } from '../../services/usuarios.service';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../../services/auth.service';
import { Usuario } from '../../models/usuario';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-form-usuarios',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './form-usuarios.component.html',
  styleUrl: './form-usuarios.component.css'
})
export class FormUsuariosComponent {


  public usuario: Usuario;
  public textoBoton: string;

  constructor(private usuarioService: UsuariosService, private ruta: Router, private ruta_activa: ActivatedRoute, private toastr: ToastrService, public authService: AuthService) {

    this.usuario = <Usuario>{};
    this.textoBoton = "";
  }

  ngOnInit() {

    this.usuario.id = this.ruta_activa.snapshot.params["id"];
    if (this.usuario.id == null) {
      this.textoBoton = "AÑADIR";
    } else {
      this.textoBoton = "MODIFICAR";

      this.usuarioService.getUsuarioPorId(this.usuario.id).subscribe({
        next: res => {
          console.log(res);
          this.usuario = res
          this.usuario.contrasena = ""
        },
        error: error => this.toastr.error(error.error.message, "ERROR")
      })
    }
  }

  onSubmit(usuario: Usuario) {
    console.log("Formulario enviado " + usuario.username);
    if (this.usuario.id == null) {
      this.usuarioService.crearUsuarios(usuario).subscribe({
        next: (res) => {
          this.toastr.success("Usuario creado correctamente", "Usuario registrado")
          return this.ruta.navigate(['/usuarios']);
        },
        error: error => {
          this.toastr.error(error.error.message, "ERROR")
        }
      })

    } else {
      this.usuarioService.modificarUsuarios(usuario, this.usuario.id).subscribe({
        next: (res) => {
          this.toastr.success("Usuario modificado correctamente", "Usuario modificado")
          if (this.authService.getTokenDescodificado().tipo_usuario == "admin") {
            return this.ruta.navigate(['/usuarios']);
          }
          this.authService.logOut();
          return this.ruta.navigate(['/login']);
        },
        error: error => {
          this.toastr.error(error.error.message, "ERROR")
        }
      })
    }
  }
}


