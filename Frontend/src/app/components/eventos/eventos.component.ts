import { Component, Input, OnInit } from '@angular/core';
import { Evento } from '../../models/evento';
import { Router, RouterLink } from '@angular/router';
import { EventosService } from '../../services/eventos.service';
import { CommonModule, DatePipe, DecimalPipe, UpperCasePipe } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-eventos',
  standalone: true,
  imports: [RouterLink, DatePipe, DecimalPipe, CommonModule, UpperCasePipe],
  templateUrl: './eventos.component.html',
  styleUrl: './eventos.component.css'
})
export class EventosComponent {

  public listaEventos: Evento[] = [];
  public listaEventosPersonales: Evento[] = [];
  public evento: Evento;
  @Input()
  public idCreador: any;
  mostrarEventosPersonales = false;
  mostrarEventosTotales = true;


  constructor(private eventoService: EventosService, private ruta: Router, public authService: AuthService) {
    this.evento = <Evento>{};
    this.getAllEventos();
  }

  getAllEventos() {
    this.eventoService.getAllEventos().subscribe({
      next: res => { this.listaEventos = res; console.log(res) },
      error: error => console.log(error)
    });
  }

  getEventosPersonales() {
    this.eventoService.getEventosPersonales().subscribe({
      next: res => { this.listaEventosPersonales = res; console.log(res) },
      error: error => console.log(error)
    });
  }

  borrarEvento(eventoBorrar: Evento) {
    console.log(eventoBorrar.idEvento, "Estamos en borrar de eventos.component");
    if (confirm("¿Seguro que desea eliminar el evento: " + eventoBorrar.titulo + "?")) {
      this.eventoService.borrarEventos(eventoBorrar.idEvento).subscribe({
        next: res => { this.getAllEventos() },
        error: error => console.log(error)
      });
    }
  }

  mostrarEventos(opcion_menu: string) {
    if (opcion_menu == "ver_todos_eventos") {
      this.mostrarEventosTotales = true;
      this.mostrarEventosPersonales = false;
      this.getAllEventos();
    } else if (opcion_menu == "ver_eventos_personales") {
      this.mostrarEventosTotales = false;
      this.mostrarEventosPersonales = true;
      this.getEventosPersonales();
    }
  }
}
