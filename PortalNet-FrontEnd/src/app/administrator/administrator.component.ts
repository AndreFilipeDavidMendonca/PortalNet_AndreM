import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { User } from '../user.model';
import {formatDate } from '@angular/common';
import { timer, Observable } from 'rxjs';

@Component({
  selector: 'app-administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.css']
})
export class AdministratorComponent implements OnInit {
  currentUser: User;
  today: number = Date.now();
 
  constructor(private authenticationService: AuthenticationService) { 
    this.currentUser = this.authenticationService.currentUserValue;     
  }

 
  ngOnInit() {
  }

}
