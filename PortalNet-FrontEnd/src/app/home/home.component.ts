import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AuthenticationService } from '../authentication.service';
import { ErrorInterceptor } from '../interceptors/error.interceptor';
import { ServicesService } from '../services.service';
import { AlertService } from '../alert.service';
import { Service } from '../service.model';
import { Client } from '../client.model';
import { Employee } from '../employee.model';
import { AppComponent } from '../app.component';
import { User } from '../user.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
 
})

export class HomeComponent implements OnInit {
  services: Service[] = [];
  client: Client;
  filteredServices: Service[] = [];
  loginForm: FormGroup;
  isLoading = false;
  submitted = false;
  currentUser: User;
  message: string;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private servicesService: ServicesService,
        private alertService: AlertService,
        private appComponent: AppComponent
    ) { 
      let currentUser = this.authenticationService.currentUserValue;
     }

  
    fetchServices() {
      this.servicesService.getHomeServices().pipe(first()).subscribe(services => {
        this.services = services;
        this.filteredServices = this.services.filter(x =>  (x.status === true));
       }); 
    }
  
  ngOnInit() {
    this.fetchServices();
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
  });

   
  // reset login status
  this.authenticationService.logout();
  }

  get f() { return this.loginForm.controls; }

  onSubmit() {
    this.submitted = true;
    this.isLoading = true;  
    
   
    // stop here if form is invalid
    if (this.loginForm.invalid) {
        return;
    }    

    this.authenticationService.login(this.f.email.value, this.f.password.value)
        .pipe(first())
        .subscribe(
          data => {
            this.appComponent.isLoggedIn();
            if(this.appComponent.isClient()) {
              this.currentUser = this.authenticationService.currentUserValue;
              this.router.navigate(['/client/', this.currentUser.userId]);
              console.log(data.message);
            } else if (this.appComponent.isEmployee() || this.appComponent.isAdmin()) {
              this.router.navigate(['/administrator']);
            }
            return data;
            },
            error => {
              this.router.navigate(['/home']);
              this.alertService.error(error);
              console.log(error);
              this.isLoading = false;
              return error;
    });
  }  
}




  




 


    


   
