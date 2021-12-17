import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "./services/token-storage.service";
import {environment} from "../environments/environment";
import {IconsService} from "./services/icons.service";
import {fadeAnimation} from "./utils/animations";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  animations: [fadeAnimation]
})
export class AppComponent implements OnInit {
  mainTitle = environment.apiName;

  constructor(private tokenStorage: TokenStorageService,
              private iconsService: IconsService,
              public router: Router) {
    this.iconsService.registerIcons();
  }

  ngOnInit(): void {
    this.iconsService.registerIcons();
    console.log(localStorage.getItem('auth-user'))
  }
  getCurrentUser() {
    return localStorage.getItem('auth-user');
  }

  printCurrentUser() {
    console.log(localStorage.getItem('auth-user'));
  }

  logout(){
    console.log("logout");
    this.tokenStorage.signOut();
  }

}
