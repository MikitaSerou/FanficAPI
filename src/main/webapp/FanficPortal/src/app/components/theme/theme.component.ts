import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ThemeService } from '../../services/theme.service';
import { Location } from '@angular/common';
import { Theme } from '../../interfaces/theme';

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
    subscribers: [],
  };

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
  }

  ngOnInit(): void {}

  goBack(): void {
    this.location.back();
  }
}
