import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {UserPageComponent} from './components/user/user-page/user-page.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LoginFormComponent} from './components/forms/login-form/login-form.component';
import {RegistrationFormComponent} from './components/forms/registration-form/registration-form.component';
import {AngularMaterialModule} from './angular-material.module';
import {AppRoutingModule} from './app-routing.module';
import {FlexLayoutModule} from '@angular/flex-layout';
import {ProfileComponent} from './components/user/profile/profile.component';
import {MainPageComponent} from './components/main-page/main-page.component';
import {ErrorPageComponent} from './components/error-page/error-page.component';
import {ThemesListComponent} from './components/themes-list/themes-list.component';
import {NavigationComponent} from './components/navigation/navigation.component';
import {LayoutModule} from '@angular/cdk/layout';
import {ThemeComponent} from './components/theme/theme.component';
import {ExitGuard} from './guards/exit.guard';
import {PublicationsListComponent} from './components/publications-list/publications-list.component';

@NgModule({
  declarations: [
    AppComponent,
    UserPageComponent,
    LoginFormComponent,
    RegistrationFormComponent,
    ProfileComponent,
    MainPageComponent,
    ErrorPageComponent,
    ThemesListComponent,
    NavigationComponent,
    ThemeComponent,
    PublicationsListComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    AngularMaterialModule,
    FlexLayoutModule,
    LayoutModule,
  ],
  providers: [ExitGuard],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AppModule {}
