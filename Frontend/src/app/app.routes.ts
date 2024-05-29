import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UsuariosComponent } from './components/usuarios/usuarios.component';
import { EventosComponent } from './components/eventos/eventos.component';
import { AdminComponent } from './components/admin/admin.component';
import { FormEventosComponent } from './components/form-eventos/form-eventos.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'register',
        component: RegisterComponent
    },
    {
        path: 'usuarios',
        component: UsuariosComponent,
        canActivate: [authGuard]
    },
    {
        path: 'eventos',
        component: EventosComponent,
        canActivate: [authGuard]
    },
    {
        path: 'admin',
        component: AdminComponent,
        canActivate: [authGuard]
    },
    {
        path: 'anadir-evento',
        component: FormEventosComponent,
        canActivate: [authGuard]
    },
    {
        path: '**',
        redirectTo: '/eventos',
        pathMatch: 'full',
    }
    
];
