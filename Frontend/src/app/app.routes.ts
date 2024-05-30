import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UsuariosComponent } from './components/usuarios/usuarios.component';
import { EventosComponent } from './components/eventos/eventos.component';
import { FormEventosComponent } from './components/form-eventos/form-eventos.component';
import { authGuard } from './guards/auth.guard';
import { FormUsuariosComponent } from './components/form-usuarios/form-usuarios.component';

export const routes: Routes = [

    //AUTHORIZATION
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'register',
        component: RegisterComponent
    },

    //USUARIOS
    {
        path: 'usuarios',
        component: UsuariosComponent,
        canActivate: [authGuard]
    },
    {
        path: 'anadir-usuario',
        component: FormUsuariosComponent,
        canActivate: [authGuard]
    },
    {
        path: 'editar-usuario/:id',
        component: FormUsuariosComponent,
        canActivate: [authGuard]
    },

    //EVENTOS
    {
        path: 'eventos',
        component: EventosComponent,
        canActivate: [authGuard]
    },
    {
        path: 'anadir-evento',
        component: FormEventosComponent,
        canActivate: [authGuard]
    },
    {
        path: 'editar-evento/:id',
        component: FormEventosComponent,
        canActivate: [authGuard]
    },

    //RUTA PREDETERMINADA
    {
        path: '**',
        redirectTo: '/eventos',
        pathMatch: 'full',
    }

];
