import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegistrationFormComponent} from './components/forms/registration-form/registration-form.component';
import {LoginFormComponent} from './components/forms/login-form/login-form.component';
import {ProfileComponent} from './components/user/profile/profile.component';
import {MainPageComponent} from './components/main-page/main-page.component';
import {ErrorPageComponent} from './components/error-page/error-page.component';
import {ThemesListComponent} from './components/themes-list/themes-list.component';
import {ThemeComponent} from './components/theme/theme.component';
import {ExitGuard} from './guards/exit.guard';
import {AuthGuard} from './guards/auth.guard';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/' },
  { path: '', component: MainPageComponent },
  { path: 'signin', component: LoginFormComponent },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  {
    path: 'register',
    component: RegistrationFormComponent,
    canDeactivate: [ExitGuard],
  },
  { path: 'themes', component: ThemesListComponent },
  { path: 'themes/:id', component: ThemeComponent },
  //error page routing should be last!!!
  { path: '**', component: ErrorPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
