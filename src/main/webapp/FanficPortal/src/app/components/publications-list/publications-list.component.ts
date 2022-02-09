import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { PublicationService } from '../../services/publication.service';
import { MatPaginator } from '@angular/material/paginator';
import { SimplePublicationDatasource } from '../theme/SimplePublicationDatasource';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-publications-list',
  styleUrls: ['./publications-list.component.sass'],
  templateUrl: './publications-list.component.html',
})
export class PublicationsListComponent implements OnInit {
  @Input() themeId: number = 0;
  displayedColumns: string[] = ['id', 'name', 'author', 'updateDate'];
  simplePublicationDatasource: SimplePublicationDatasource;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private publicationService: PublicationService) {}

  ngOnInit(): void {
    this.simplePublicationDatasource = new SimplePublicationDatasource(
      this.publicationService
    );
    this.simplePublicationDatasource.loadPublications(this.themeId);
  }

  ngAfterViewInit() {
    this.simplePublicationDatasource.counter$
      .pipe(
        tap((count) => {
          this.paginator.length = count;
        })
      )
      .subscribe();

    this.paginator.page.pipe(tap(() => this.loadPublications())).subscribe();
  }

  loadPublications() {
    this.simplePublicationDatasource.loadPublications(
      this.themeId,
      this.paginator.pageIndex,
      this.paginator.pageSize
    );
  }
}
