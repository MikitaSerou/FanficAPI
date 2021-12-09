import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../services/auth.service";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Validation} from "../../../utils/validation";
import {UserService} from "../../../services/user.service";


@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent{

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
      validators: [Validation.match('password', 'passwordConfirm')]
    });
  }
  get formControls(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit(): void {
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

  //check user by username
  checkUserName(username: string): void {
    this.userService.existByUsername(username).subscribe(
      data => {
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
  }
}
