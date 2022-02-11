import { Component, OnInit } from '@angular/core';
import { IconService } from './services/icon.service';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';
import { fadeAnimation } from './utils/animations';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  animations: [fadeAnimation],
})
export class AppComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private iconsService: IconService,
    public router: Router
  ) {
    this.iconsService.registerIcons();
  }

  ngOnInit(): void {
    this.iconsService.registerIcons();
    console.log(localStorage.getItem('auth-user'));
  }
}
