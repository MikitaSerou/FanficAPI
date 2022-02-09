import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {SimplePublication} from '../interfaces/simple/SimplePublication';
import {Page} from '../interfaces/Page';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class PublicationService {
  constructor(private http: HttpClient) {}

  public getPublicationsByThemeId(
    themeId: number,
    page?: number,
    size?: number,
    descSort = false
  ) {
    //default values on backend is: page = 0, size = 10, descSort = false
    let params: HttpParams = new HttpParams();
    if (page) {
      params = params.append('page', page.toString());
    }
    if (size) {
      params = params.append('size', size.toString());
    }
    if (descSort) {
      params = params.append('descSort', descSort.toString());
    }
    return this.http.get<Page<SimplePublication>>(
      `${environment.baseUrl}/publication/theme/${themeId}`,
      { params: params, headers: httpOptions.headers }
    );
  }
}
