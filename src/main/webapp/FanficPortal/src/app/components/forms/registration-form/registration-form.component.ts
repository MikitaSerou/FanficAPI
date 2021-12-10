import {Component} from '@angular/core';
import {AuthService} from "../../../services/auth.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../services/user.service";
import Validation from "../../../utils/validation";

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent {

  hide = true;
  form: FormGroup;
  submitted: boolean = false;
  username: FormControl = new FormControl('', [Validators.required, Validators.maxLength(20)]);
  email: FormControl = new FormControl('', [Validators.required, Validators.email]);
  password: FormControl = new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(40)]);
  confirmPassword: FormControl = new FormControl('', Validators.required);
  acceptTerms: FormControl = new FormControl(false, Validators.requiredTrue);

  constructor(private authService: AuthService,
              private userService: UserService,
              private formBuilder: FormBuilder) {

    this.form = this.formBuilder.group({
      username: this.username,
      email: this.email,
      password: this.password,
      confirmPassword: this.confirmPassword,
      acceptTerms: this.acceptTerms,
    }, {
      validators: [Validation.match('password', 'confirmPassword')]
    });
  }

  onSubmit(): void {
    this.checkUserNameBeforeRegistration(this.username.value);
    this.checkEmailBeforeRegistration(this.email.value);
    this.submitted = true;
    if (this.form.invalid) {
      /*      console.log('username');
            console.log( this.username.errors);
            console.log("email");
            console.log( this.email.errors);
            console.log("password" );
            console.log( this.password.errors);
            console.log("confirmPassword");
            console.log(this.confirmPassword.errors);
            console.log("acceptTerms");
            console.log(this.acceptTerms.errors);
            console.log('invalid');*/
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
