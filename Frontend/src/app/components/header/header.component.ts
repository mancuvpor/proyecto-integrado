import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import {
  trigger,
  state,
  style,
  animate,
  transition,
} from '@angular/animations';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
  animations: [
    trigger('rotatedState', [
      state('default', style({ transform: 'rotate(0)' })),
      state('rotated', style({ transform: 'rotate(-180deg)' })),
      transition('rotated => default', animate('300ms ease-out')),
      transition('default => rotated', animate('300ms ease-in')),
    ]),
  ],
})


export class HeaderComponent {

  state: string = 'default';

  constructor(public authService: AuthService) {

  }

  // Add the state to rotate the icon
  rotate() {
    this.state = this.state === 'default' ? 'rotated' : 'default';
  }


}
