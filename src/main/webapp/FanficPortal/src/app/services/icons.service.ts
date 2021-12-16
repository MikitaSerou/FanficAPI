import { Injectable } from '@angular/core';
import {DomSanitizer} from "@angular/platform-browser";
import {MatIconRegistry} from "@angular/material/icon";
import {Icons} from "../enum/icons";

@Injectable({
  providedIn: 'root'
})
export class IconsService {

  constructor(  private matIconRegistry: MatIconRegistry,
                private domSanitizer: DomSanitizer) {}

  public registerIcons(): void {
    console.log(Object.values(Icons));
    this.loadIcons(Object.values(Icons), '../assets/icons');
  }

  private loadIcons(iconKeys: string[], iconUrl: string): void {

    iconKeys.forEach(key => {
      console.log(`${iconUrl}/${key}.svg`);
      this.matIconRegistry.addSvgIcon(key, this.domSanitizer.bypassSecurityTrustResourceUrl(`${iconUrl}/${key}.svg`));
    });
  }
}
