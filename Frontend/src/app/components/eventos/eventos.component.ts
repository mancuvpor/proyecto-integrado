import { Component, Input, OnInit } from '@angular/core';
import { Evento } from '../../models/evento';
import { Router, RouterLink } from '@angular/router';
import { EventosService } from '../../services/eventos.service';
import { DatePipe, DecimalPipe } from '@angular/common';

@Component({
  selector: 'app-eventos',
  standalone: true,
  imports: [RouterLink, DatePipe, DecimalPipe],
  templateUrl: './eventos.component.html',
  styleUrl: './eventos.component.css'
})
export class EventosComponent {

  public listaEventos: Evento[] = [];
  public evento: Evento;
  @Input()
  public idCreador: any;


  constructor(private eventoService: EventosService, private ruta: Router) {
    this.evento = <Evento>{};
    this.getAllEventos();
  }

  getAllEventos() {
    this.eventoService.getAllEventos().subscribe({
      next: res => { this.listaEventos = res; console.log(res) },
      error: error => console.log(error)
    });
  }

  borrarEvento(eventoBorrar: Evento) {
    console.log(this.evento.idEvento, "Estamos en borrar de eventos.component");
    if (confirm("¿Seguro que desea eliminar el evento: " + eventoBorrar.titulo + "?")) {
      this.eventoService.borrarEventos(eventoBorrar.idEvento).subscribe({
        next: res => { this.getAllEventos() },
        error: error => console.log(error)
      });
    }
  }
}
