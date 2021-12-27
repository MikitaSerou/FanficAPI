import {Component, OnInit} from '@angular/core';
import {ThemeService} from "../../services/theme.service";
import {SimpleTheme} from "../../interfaces/simple/simpleTheme";

@Component({
  selector: 'app-themes-list',
  templateUrl: './themes-list.component.html',
  styleUrls: ['./themes-list.component.sass']
})
export class ThemesListComponent implements OnInit {
  themes: SimpleTheme[] = [];
  gridColumns: number = 6;
  breakpoint: number;

  constructor(private themeService: ThemeService) {
    this.breakpoint = (window.innerWidth <= 960) ? 5 : 6;
  }

  ngOnInit(): void {
    this.themeService.getAllPreviews().subscribe(
      (response: SimpleTheme[]) => {
        this.themes = response;
      },
      error => {
        console.log(error);
      }
    );
  }

  onResize({event}: { event: any }) {
    this.breakpoint = (event.target.innerWidth <= 960) ? 5 : 6;
  }

  //Round off the number and add k when the number is over 1000 and cut off the last 3 digits
  roundOff(number: number): string {
    if (number > 1000) {
      return (Math.round(number / 100) / 10).toString() + 'k';
    } else {
      return number.toString();
    }
  }

}
