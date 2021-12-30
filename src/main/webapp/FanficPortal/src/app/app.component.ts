import {Component, OnInit} from '@angular/core';
import {IconsService} from './services/icons.service';
import {Router} from '@angular/router';
import {AuthService} from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  constructor(
    private authService: AuthService,
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
