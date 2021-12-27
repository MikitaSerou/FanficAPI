import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './services/token-storage.service';
import { IconsService } from './services/icons.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  constructor(
    private tokenStorage: TokenStorageService,
    private iconsService: IconsService,
    public router: Router
  ) {
    this.iconsService.registerIcons();
  }

  ngOnInit(): void {
    this.iconsService.registerIcons();
    console.log(localStorage.getItem('auth-user'));
  }
}
