import { Component } from '@angular/core';

import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { TokenStorageService } from '../../../services/token-storage.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.sass'],
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

  constructor(
    private formBuilder: FormBuilder,
    private tokenStorage: TokenStorageService,
    private authService: AuthService,
    private _snackBar: MatSnackBar,
    private router: Router
  ) {
    this.loginForm = this.formBuilder.group({
      username: this.username,
      password: this.password,
    });
  }

  openLoginFailedSnackBar() {
    if (this.isLoginFailed)
      this._snackBar.open('Login failed. Check your credentials.', '', {
        panelClass: ['snackbar-error'],
        duration: 3000,
      });
  }

  onSubmit(): void {
    this.authService
      .login(this.loginForm.value.username, this.loginForm.value.password)
      .subscribe(
        (data) => {
          this.tokenStorage.saveToken(data.token);
          this.tokenStorage.saveUser(data);
          this.isLoginFailed = false;
          this.isLoggedIn = true;
          this.roles = this.tokenStorage.getUser().roles;
          this.router.navigate(['/']);
        },
        (error) => {
          this.errorMessage = error.message;
          this.isLoginFailed = true;
          console.log(error);
          this.openLoginFailedSnackBar();
        }
      );
  }

  reloadPage(): void {
    window.location.reload();
  }
}
