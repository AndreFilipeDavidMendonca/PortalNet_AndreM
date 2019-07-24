import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { Client } from '../client.model';
import { Employee } from '../employee.model';

@Injectable({ providedIn: 'root' })
export class LoginGuard implements CanActivate {
    client: Client;
    employee: Employee;
    
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const currentUser = this.authenticationService.currentUserValue;
    if (currentUser) {
      this.router.navigate(['/administrator']);
      return false;
    }
    return true;
  }
}