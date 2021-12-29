import { Component } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { UserService } from '../../../services/user.service';
import Validation from '../../../utils/validation';
import { ComponentCanDeactivate } from '../../../guards/exit.guard';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { TokenStorageService } from '../../../services/token-storage.service';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.sass'],
})
export class RegistrationFormComponent implements ComponentCanDeactivate {
  registrationForm: FormGroup;
  hide: boolean = true;
  submitted: boolean = false;
  userIsRegistered: boolean = false;
  username: FormControl = new FormControl('', [
    Validators.required,
    Validators.maxLength(20),
  ]);
  email: FormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);
  birthDate: FormControl = new FormControl('', [Validators.required]);
  password: FormControl = new FormControl('', [
    Validators.required,
    Validators.minLength(8),
    Validators.maxLength(40),
  ]);
  confirmPassword: FormControl = new FormControl('', Validators.required);
  acceptTerms: FormControl = new FormControl(false, Validators.requiredTrue);

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private formBuilder: FormBuilder,
    private _snackBar: MatSnackBar,
    private router: Router,
    private tokenStorage: TokenStorageService
  ) {
    this.registrationForm = this.formBuilder.group(
      {
        username: this.username,
        email: this.email,
        password: this.password,
        birthDate: this.birthDate,
        confirmPassword: this.confirmPassword,
        acceptTerms: this.acceptTerms,
      },
      {
        validators: [Validation.match('password', 'confirmPassword')],
      }
    );
  }

  onSubmit(): void {
    this.checkUserNameBeforeRegistration(this.username.value);
    this.checkEmailBeforeRegistration(this.email.value);
    if (!this.birthDate.errors) {
      this.checkDateBeforeRegistration(this.birthDate.value);
    }
    this.submitted = true;
    console.log(this.birthDate.value);
    if (this.registrationForm.invalid) {
      return;
    }
    this.registerUser(
      this.username.value,
      this.email.value,
      this.password.value,
      this.birthDate.value,
      this.confirmPassword.value
    );
    //this.loginNewUser(this.username.value, this.password.value);
  }

  onReset(): void {
    this.submitted = false;
    this.registrationForm.reset();
  }

  checkDateBeforeRegistration(date: Date): void {
    date.getTime() > new Date().getTime()
      ? this.birthDate.setErrors({ birthDateMoreThanNow: true })
      : this.birthDate.setErrors(null);
  }

  checkUserNameBeforeRegistration(username: string): void {
    if (username) {
      this.userService.existByUsername(username).subscribe(
        (data) => {
          if (data) {
            this.username.setErrors({ usernameIsTaken: true });
          } else {
            this.username.setErrors(null);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }

  checkEmailBeforeRegistration(email: string): void {
    if (email) {
      this.userService.existByEmail(email).subscribe(
        (data) => {
          data
            ? this.email.setErrors({ emailIsTaken: true })
            : this.email.setErrors(null);
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }

  registerUser(
    username: string,
    email: string,
    password: string,
    birthDate: Date,
    confirmPassword: string
  ): void {
    this.authService
      .signup(username, email, password, birthDate, confirmPassword)
      .subscribe(
        (data) => {
          console.log(data);
          this.loginNewUser(username, password);
        },
        (error) => {
          this.openRegistrationFailedSnackBar(error.error.message);
          console.log(error);
        }
      );
  }

  loginNewUser(username: string, password: string): void {
    this.authService.login(username, password).subscribe(
      (data) => {
        console.log(data);
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveUser(data);
        this.router.navigate(['/']);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  canDeactivate(): boolean | Observable<boolean> {
    if (!this.submitted && this.registrationForm.dirty) {
      return confirm(
        'Are you want to leave this page without saving inputted data?'
      );
    } else {
      return true;
    }
  }

  openRegistrationFailedSnackBar(message: string) {
    this._snackBar.open(message, '', {
      panelClass: ['snackbar-error'],
      duration: 3000,
    });
  }
}
