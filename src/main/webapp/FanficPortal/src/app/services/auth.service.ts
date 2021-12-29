import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  isLogin: boolean = false;

  constructor(
    private http: HttpClient,
    private tokenService: TokenStorageService
  ) {}

  public login(username: string, password: string): Observable<any> {
    this.isLogin = true;
    return this.http.post(
      `${environment.baseUrl}/auth/login`,
      { username, password },
      httpOptions
    );
  }

  isLoggedIn(): boolean {
    const loggedIn = this.tokenService.getUser();
    this.isLogin = loggedIn != null;
    return this.isLogin;
  }

  getCurrentUserRoles(): string[] {
    const user = this.tokenService.getUser();
    if (user) {
      return user.roles;
    }
    return [];
  }

  public signup(
    username: string,
    email: string,
    password: string,
    birthDate: Date,
    confirmPassword: string
  ): Observable<any> {
    return this.http.post(
      `${environment.baseUrl}/auth/signup`,
      { username, email, password, birthDate, confirmPassword },
      httpOptions
    );
  }

  public refreshTokens(refreshToken: string): Observable<any> {
    return this.http.post(
      `${environment.baseUrl}/auth/refresh`,
      { refreshToken },
      httpOptions
    );
  }

  logout() {
    window.localStorage.clear();
  }
}
