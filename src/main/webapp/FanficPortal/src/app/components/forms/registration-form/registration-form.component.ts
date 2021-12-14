import {Component} from '@angular/core';
import {AuthService} from "../../../services/auth.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../services/user.service";
import Validation from "../../../utils/validation";

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.sass']
})
export class RegistrationFormComponent {

  registrationForm: FormGroup;
  hide: boolean = true;
  submitted: boolean = false;
  username: FormControl = new FormControl('', [Validators.required, Validators.maxLength(20)]);
  email: FormControl = new FormControl('', [Validators.required, Validators.email]);
  birthDate: FormControl = new FormControl('', [Validators.required]);
  password: FormControl = new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(40)]);
  confirmPassword: FormControl = new FormControl('', Validators.required);
  acceptTerms: FormControl = new FormControl(false, Validators.requiredTrue);

  constructor(private authService: AuthService,
              private userService: UserService,
              private formBuilder: FormBuilder) {

    this.registrationForm = this.formBuilder.group({
      username: this.username,
      email: this.email,
      password: this.password,
      birthDate: this.birthDate,
      confirmPassword: this.confirmPassword,
      acceptTerms: this.acceptTerms,
    }, {
      validators: [Validation.match('password', 'confirmPassword')],
    });
  }


  onSubmit(): void {
    this.checkUserNameBeforeRegistration(this.username.value);
    this.checkEmailBeforeRegistration(this.email.value);
    if (!this.birthDate.errors){this.checkDateBeforeRegistration(this.birthDate.value);}

    this.submitted = true;
    console.log(this.birthDate.value)
    if (this.registrationForm.invalid) {
      return;
    }
    console.log(JSON.stringify(this.registrationForm.value, null, 2));
  }

  onReset(): void {
    this.submitted = false;
    this.registrationForm.reset();
  }

  checkDateBeforeRegistration(date: Date): void {
    date.getTime() > new Date().getTime() ?
      this.birthDate.setErrors({'birthDateMoreThanNow': true}) :
      this.birthDate.setErrors(null);
  }

  checkUserNameBeforeRegistration(username: string): void {
    if (username) {
      this.userService.existByUsername(username).subscribe(
        data => {
          if (data) {
            this.username.setErrors({'usernameIsTaken': true})
          } else {
            this.username.setErrors(null);
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
          data ? this.email.setErrors({'emailIsTaken': true}) :
            this.email.setErrors(null);
        },
        error => {
          console.log(error);
        }
      );
    }
  }
}
