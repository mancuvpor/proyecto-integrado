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

  constructor(private usuarioService: UsuariosService, private ruta: Router, private ruta_activa: ActivatedRoute, private toastr: ToastrService, private authService: AuthService) {

    this.usuario = <Usuario>{};
    this.textoBoton = "";
  }

  ngOnInit() {

    this.usuario.id = this.ruta_activa.snapshot.params["id"];
    if (this.usuario.id == null) {
      this.textoBoton = "Añadir";
    } else {
      this.textoBoton = "Modificar";

      this.usuarioService.getUsuarioPorId(this.usuario.id).subscribe({
        next: res => {
          console.log(res);
          this.usuario = res
        },
        error: error => console.log(error)
      })
    }
  }

  onSubmit(usuario: Usuario) {
    console.log("Formulario enviado " + usuario);
    if (this.usuario.id == null) {
      usuario.id = this.authService.getTokenDescodificado().id;
      this.usuarioService.crearUsuarios(usuario).subscribe({
        next: (res) => {
          this.toastr.success("Usuario creado correctamente", "Usuario registrado")
          return this.ruta.navigate(['/usuarios']);
        },
        error: error => {
          this.toastr.error(error.error.message, "ERROR")
          console.log(error)
        }
      })

    } else {
      this.usuarioService.modificarUsuarios(usuario, this.usuario.id).subscribe({
        next: (res) => {
          this.toastr.success("Usuario modificado correctamente", "Usuario modificado")
          return this.ruta.navigate(['/usuarios']);
        },
        error: error => {
          this.toastr.error(error.error.message, "ERROR")
          console.log(error)
        }
      })
    }
  }
}


