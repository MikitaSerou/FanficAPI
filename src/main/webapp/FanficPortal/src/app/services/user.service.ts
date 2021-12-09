import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../interfaces/user";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  public getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${environment.baseUrl}/user/page/${id}`);
  }

  public existByUsername(username: string): Observable<boolean> {
    return this.http.get<boolean>(`${environment.baseUrl}/user/exist/${username}`);
  }
}
