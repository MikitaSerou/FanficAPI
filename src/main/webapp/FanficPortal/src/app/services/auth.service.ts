import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  public login(username: string, password: string) {
    return this.http.post(`${environment.baseUrl}/auth/login`, {username, password});
  }

  public signup(username: string, email: string, password: string, roles: string[]) {
    return this.http.post(`${environment.baseUrl}/auth/signup`, {username, email, password, roles});
  }

  public refreshTokens(refreshToken: string) {
    return this.http.post(`${environment.baseUrl}/auth/refresh`, {refreshToken});
  }

}
