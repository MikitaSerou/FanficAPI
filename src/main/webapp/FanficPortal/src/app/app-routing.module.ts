import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegistrationFormComponent} from "./components/forms/registration-form/registration-form.component";
import {LoginFormComponent} from "./components/forms/login-form/login-form.component";

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'signin' },
  { path: 'signin', component: LoginFormComponent },
  { path: 'register', component: RegistrationFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
