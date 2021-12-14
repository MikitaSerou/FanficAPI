import {Component} from '@angular/core';

import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../services/auth.service";
import {TokenStorageService} from "../../../services/token-storage.service";


@Component({
  selector: 'login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.sass']
})
export class LoginFormComponent {

  hide: boolean = true;
  loginForm: FormGroup;
  username: FormControl = new FormControl('', [Validators.required]);
  password: FormControl = new FormControl('', [Validators.required]);
  submitted: boolean = false;

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private formBuilder: FormBuilder,
              private tokenStorage: TokenStorageService,
              private authService: AuthService,
              ) {
    this.loginForm = this.formBuilder.group({
      username: this.username,
      password: this.password
    });
  }

  onSubmit(): void {

    this.authService.login(this.loginForm.value.username, this.loginForm.value.password).subscribe(
      data => {
        console.log("Token ebat: " + data.token);
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
      },
      error => {
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
        console.log(error);
      }
    );
  }
}
