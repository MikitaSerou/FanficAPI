import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  public login(username: string, password: string) : Observable<any> {
    return this.http.post(`${environment.baseUrl}/auth/login`, {username, password}, httpOptions);
  }

  public signup(username: string, email: string, password: string, roles: string[]) : Observable<any> {
    return this.http.post(`${environment.baseUrl}/auth/signup`, {username, email, password, roles}, httpOptions);
  }

  public refreshTokens(refreshToken: string) : Observable<any> {
    return this.http.post(`${environment.baseUrl}/auth/refresh`, {refreshToken}, httpOptions);
  }

}
