import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    return this.checkUserLogin();
  }

  checkUserLogin(): boolean {
    console.log('checkUserLogin in guard');
    if (this.authService.isLoggedIn()) {
      const userRoles: string[] = this.authService.getCurrentUserRoles();
      console.log('userRoles: ', userRoles);
      if (userRoles.includes('ROLE_USER')) {
        console.log(userRoles.includes('ROLE_USER'));
        return true;
      }
      this.router.navigate(['/signin']);
      return false;
    }
    this.router.navigate(['/signin']);
    return false;
  }
}
