import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router, RouterLink } from '@angular/router';
import { Usuario } from '../../models/usuario';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, HttpClientModule, RouterLink],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  public register: Usuario

  constructor(private authService: AuthService, private ruta: Router, private toastr: ToastrService) {

    this.register = <Usuario>{}
  }


  onRegister() {
    this.authService.register(this.register).subscribe({
      next: (res) => {
        console.log(res)
        localStorage.setItem('token', res.token);
        this.toastr.success("Usuario creado correctamente", "Usuario registrado")
        return this.ruta.navigate(['/login']);
      },
      error: error => {
        this.toastr.error(error.error.message, "ERROR")
        console.log(error)
      }
    })

  }

}
