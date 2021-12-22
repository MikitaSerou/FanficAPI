import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {SimpleTheme} from "../interfaces/simple/simpleTheme";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ThemeService {

  constructor(private http: HttpClient) {
  }

  public getAllPreviews(): Observable<SimpleTheme[]>{
    return this.http.get<SimpleTheme[]>(`${environment.baseUrl}/theme/allPreviews`, httpOptions);
  }
}
