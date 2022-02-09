import {CollectionViewer, DataSource} from '@angular/cdk/collections';
import {SimplePublication} from '../../interfaces/simple/SimplePublication';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {PublicationService} from '../../services/publication.service';
import {catchError, finalize} from 'rxjs/operators';
import {Page} from '../../interfaces/Page';

export class SimplePublicationDatasource
  implements DataSource<SimplePublication>
{
  private publicationSubject = new BehaviorSubject<SimplePublication[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);
  private countSubject = new BehaviorSubject<number>(0);
  public counter$ = this.countSubject.asObservable();

  constructor(private publicationService: PublicationService) {}

  connect(collectionViewer: CollectionViewer): Observable<SimplePublication[]> {
    return this.publicationSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.publicationSubject.complete();
    this.loadingSubject.complete();
    this.countSubject.complete();
  }

  loadPublications(themeId: number, pageNumber = 0, pageSize = 10) {
    this.loadingSubject.next(true);
    this.publicationService
      .getPublicationsByThemeId(themeId, pageNumber, pageSize)
      .pipe(
        catchError(() => of([])),
        finalize(() => this.loadingSubject.next(false))
      )
      .subscribe((result: any | Page<SimplePublication>) => {
        this.publicationSubject.next(result.content);
        this.countSubject.next(result.totalElements);
      });
  }
}
