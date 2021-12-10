import {Component} from '@angular/core';
import {AuthService} from "../../../services/auth.service";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../services/user.service";
import Validation from "../../../utils/validation";


@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent {

  form: FormGroup;
  submitted: boolean = false;

  constructor(private authService: AuthService,
              private userService: UserService,
              private formBuilder: FormBuilder) {

    this.form = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(40)]],
      confirmPassword: ['', Validators.required],
      acceptTerms: [false, Validators.requiredTrue]
    }, {
      validators: [Validation.match('password', 'confirmPassword')]
    });
  }

  get formControls(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit(): void {
    this.checkUserNameBeforeRegistration(this.formControls['username'].value);
    this.checkEmailBeforeRegistration(this.formControls['email'].value);
    this.submitted = true;
    if (this.form.invalid) {
      return;
    }
    console.log(JSON.stringify(this.form.value, null, 2));
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }

  checkUserNameBeforeRegistration(username: string): void {
    if (username) {
      this.userService.existByUsername(username).subscribe(
        data => {
          if (data) {
            this.formControls['username'].setErrors({'usernameIsTaken': true})
          } else {
            this.formControls['username'].setErrors(null);
          }
        },
        error => {
          console.log(error);
        }
      );
    }
  }

  checkEmailBeforeRegistration(email: string): void {
    if (email) {
      this.userService.existByEmail(email).subscribe(
        data => {
          data ? this.formControls['email'].setErrors({'emailIsTaken': true}) :
            this.formControls['email'].setErrors(null);
        },
        error => {
          console.log(error);
        }
      );
    }
  }
}
