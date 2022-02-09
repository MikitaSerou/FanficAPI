import {Component, OnInit} from '@angular/core';
import {User} from '../../../interfaces/User';
import {UserService} from '../../../services/user.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.sass'],
})
export class UserPageComponent implements OnInit {
  public user!: User;

  //  public username?: string

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.getUserById(1);
  }

  public getUserById(id: number): void {
    this.userService.getUserById(id).subscribe(
      (response: User) => {
        this.user = response;
        //   this.username = response.username;
        console.log(this.user);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
