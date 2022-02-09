import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ThemeService} from '../../services/theme.service';
import {Location} from '@angular/common';
import {Theme} from '../../interfaces/Theme';

@Component({
  selector: 'app-theme',
  templateUrl: './theme.component.html',
  styleUrls: ['./theme.component.sass'],
})
export class ThemeComponent implements OnInit {
  theme: Theme = {
    id: 0,
    name: '',
    imageUrl: '',
    publications: [],
    countOfSubscribers: 0,
    tags: [],
  };
  breakpoint: number;
  panelOpenState = false;

  constructor(
    private route: ActivatedRoute,
    private themeService: ThemeService,
    private location: Location
  ) {
    this.themeService.getById(this.route.snapshot.params.id).subscribe(
      (response: Theme) => {
        this.theme = response;
      },
      (error) => {
        console.log(error);
      }
    );
    this.breakpoint = window.innerWidth <= 960 ? 1 : 2;
  }

  ngOnInit(): void {}

  goBack(): void {
    this.location.back();
  }

  onResize({ event }: { event: any }) {
    this.breakpoint = event.target.innerWidth <= 960 ? 1 : 2;
  }
}
