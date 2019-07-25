import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { User } from '../user.model';

@Component({
  selector: 'app-administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.css']
})
export class AdministratorComponent implements OnInit {
  currentUser: User;

  constructor(private authenticationService: AuthenticationService) { 
    this.currentUser = this.authenticationService.currentUserValue;
  }

  ngOnInit() {
  }

}
