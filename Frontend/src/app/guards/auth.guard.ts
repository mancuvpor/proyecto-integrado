import { CanActivateFn } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { inject } from '@angular/core';
import { Router } from '@angular/router';

// Guardian que comprueba si estas loggeado o no, para poder viajar entre las rutas
export const authGuard: CanActivateFn = (route, state) => {
  
  if (inject(AuthService).loggedIn()) {
    return true;
  }

  inject(Router).navigate(["/login"]);
  return false;
}
