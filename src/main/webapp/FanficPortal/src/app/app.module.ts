import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

import {AppComponent} from './app.component';
import {UserPageComponent} from './components/user/user-page/user-page.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { LoginFormComponent } from './components/forms/login-form/login-form.component';
import { RegistrationFormComponent } from './components/forms/registration-form/registration-form.component';
import {AngularMaterialModule} from "./angular-material.module";
import {AppRoutingModule} from "./app-routing.module";


@NgModule({
  declarations: [
    AppComponent,
    UserPageComponent,
    LoginFormComponent,
    RegistrationFormComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    AngularMaterialModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule {
}
