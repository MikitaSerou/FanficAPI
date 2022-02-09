import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../interfaces/User';
import { environment } from '../../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  public getUserById(id: number): Observable<User> {
    return this.http.get<User>(
      `${environment.baseUrl}/user/page/${id}`,
      httpOptions
    );
  }

  public existByUsername(username: string): Observable<boolean> {
    return this.http.get<boolean>(
      `${environment.baseUrl}/user/exist/username/${username}`,
      httpOptions
    );
  }

  public existByEmail(email: string): Observable<boolean> {
    return this.http.get<boolean>(
      `${environment.baseUrl}/user/exist/email/${email}`,
      httpOptions
    );
  }
}
