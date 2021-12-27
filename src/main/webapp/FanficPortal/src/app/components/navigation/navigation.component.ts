import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { environment } from '../../../environments/environment';
import { TokenStorageService } from '../../services/token-storage.service';
import { Router } from '@angular/router';
import { fadeAnimation } from '../../utils/animations';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.sass'],
  animations: [fadeAnimation],
})
export class NavigationComponent {
  mainTitle = environment.apiName;

  isHandset$: Observable<boolean> = this.breakpointObserver
    .observe(Breakpoints.Handset)
    .pipe(
      map((result) => result.matches),
      shareReplay()
    );

  constructor(
    private breakpointObserver: BreakpointObserver,
    private tokenStorage: TokenStorageService,
    public router: Router
  ) {}
  getCurrentUser() {
    return localStorage.getItem('auth-user');
  }

  logout() {
    console.log('logout');
    this.tokenStorage.signOut();
  }
}
