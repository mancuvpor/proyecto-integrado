import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Evento } from '../../models/evento';
import { EventosService } from '../../services/eventos.service';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-form-eventos',
  standalone: true,
  imports: [RouterLink, FormsModule],
  templateUrl: './form-eventos.component.html',
  styleUrl: './form-eventos.component.css'
})
export class FormEventosComponent {

  public evento: Evento;
  public textoBoton: string;

  constructor(private eventoService: EventosService, private ruta: Router, private ruta_activa: ActivatedRoute, private toastr: ToastrService, private authService: AuthService) {

    this.evento = <Evento>{};
    this.textoBoton = "";
  }

  ngOnInit() {

    this.evento.idEvento = this.ruta_activa.snapshot.params["id"];
    if (this.evento.idEvento == null) {
      this.textoBoton = "Añadir";
    } else {
      this.textoBoton = "Modificar";

      this.eventoService.getEventoPorId(this.evento.idEvento).subscribe({
        next: res => {
          console.log(res);
          this.evento = res
        },
        error: error => console.log(error)
      })
    }
  }

  onSubmit(evento: Evento) {
    console.log("Formulario enviado " + evento);
    if (this.evento.idEvento == null) {
      evento.creador_id = this.authService.getTokenDescodificado().id;
      this.eventoService.crearEventos(evento).subscribe({
        next: (res) => {
          this.toastr.success("Evento creado correctamente", "Evento registrado")
          return this.ruta.navigate(['/eventos']);
        },
        error: error => {
          this.toastr.error(error.error.message, "ERROR")
          console.log(error)
        }
      })

    } else {
      this.eventoService.modificarEventos(evento, this.evento.idEvento).subscribe({
        next: (res) => {
          this.toastr.success("Evento modificado correctamente", "Evento modificado")
          return this.ruta.navigate(['/eventos']);
        },
        error: error => {
          this.toastr.error(error.error.message, "ERROR")
          console.log(error)
        }
      })
    }
  }
}

