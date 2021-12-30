import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};
const TOKEN_KEY: string = 'auth-token';
const USER_KEY: string = 'auth-user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  isLogin: boolean = false;

  public saveToken(token: string) {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return window.localStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user: any) {
    window.localStorage.removeItem(USER_KEY);
    window.localStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    const user = window.localStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }
    return null;
  }

  constructor(private http: HttpClient) {}

  public login(username: string, password: string): Observable<any> {
    this.isLogin = true;
    return this.http.post(
      `${environment.baseUrl}/auth/login`,
      { username, password },
      httpOptions
    );
  }

  isLoggedIn(): boolean {
    const loggedIn = this.getUser();
    this.isLogin = loggedIn != null;
    return this.isLogin;
  }

  getCurrentUserRoles(): string[] {
    const user = this.getUser();
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
