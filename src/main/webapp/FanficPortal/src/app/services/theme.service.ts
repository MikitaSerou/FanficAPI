import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { SimpleTheme } from '../interfaces/simple/SimpleTheme';
import { Theme } from '../interfaces/Theme';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class ThemeService {
  constructor(private http: HttpClient) {}

  public getAllPreviews(): Observable<SimpleTheme[]> {
    return this.http.get<SimpleTheme[]>(
      `${environment.baseUrl}/theme/allPreviews`,
      httpOptions
    );
  }

  public getById(id: number): Observable<Theme> {
    return this.http.get<Theme>(
      `${environment.baseUrl}/theme/page/${id}`,
      httpOptions
    );
  }
}
