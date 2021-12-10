import {Component, HostBinding} from '@angular/core';
import {
  trigger,
  state,
  style,
  animate,
  transition,
  // ...
} from '@angular/animations';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../services/user.service";
import {AuthService} from "../../../services/auth.service";


@Component({
  selector: 'login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent{

  myForm: FormGroup;

  constructor(private userService: UserService, private authService: AuthService) {
    this.myForm = new FormGroup({
      "username": new FormControl('', [Validators.required, Validators.max(20)]),
      "password": new FormControl('', [Validators.required, Validators.max(50)])
    });
  }

  submit(): void {
    this.authService.login(this.myForm.value.username, this.myForm.value.password).subscribe(
      data => {
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
  }
}
