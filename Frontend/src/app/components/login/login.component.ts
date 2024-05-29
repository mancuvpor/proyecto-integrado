import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Login } from '../../models/login';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, HttpClientModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  public login: Login;
  

  constructor(private authService: AuthService, private ruta: Router) {
    this.login = <Login>{}
  }

  onLogin() {
    this.authService.login(this.login).subscribe({
      next: (res) => {
        console.log(res)
        localStorage.setItem('token', res.token);
        return this.ruta.navigate(['/dashboard']);
      },
      error: error => console.log(error)
    })
  }

}



