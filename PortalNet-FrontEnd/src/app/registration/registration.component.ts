import { Component,  OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ClientService } from '../client.service';
import { AlertService } from '../alert.service';
import { first } from 'rxjs/operators';
import { AlertComponent } from '../alerts/alert.component';
import { Client } from '../client.model';
import { Service } from '../service.model';
import { ServicesService } from '../services.service';
import { UtilsService } from '../utils.service';




@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit{
  serviceData: Service;
  service: Service;
  servicePrice: number;
  services: Service[] = [];
  submitted = false;
  ClientForm: FormGroup;
  selectedService: string;
  clientToJSON: string;
  isLoading = false;
  error = '';
  success = '';
  client: Client;
  message: string;
  filteredServices: Service[] = [];

  constructor(private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private clientService: ClientService,
    private alertService: AlertService,
    private servicesService: ServicesService,
    private utilsService: UtilsService) { }



  onReset() {
    this.ClientForm.reset();
  }

  fetchServices() {
    this.servicesService.getHomeServices().pipe(first()).subscribe(services => {
      this.services = services;
      this.filteredServices = this.services.filter(x =>  (x.status === true));
     });

  }

  fetchByServiceName(){
    this.selectedService = this.ClientForm.get('serviceName').value;
    this.servicesService.getByName(this.selectedService)
      .pipe(first())
      .subscribe(service => {
        this.service = service;
        this.servicePrice = this.service.price;
        return this.servicePrice;
      });
  }

  ngOnInit(){
    this.fetchServices();
    this.ClientForm = this.formBuilder.group({
      name: ['', Validators.required],
      nif: ['', [Validators.required, Validators.minLength(9), Validators.maxLength(9)]],
      address: ['', Validators.required],
      postalCode: ['', Validators.required],
      city: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(15)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(15)]],
      mobilePhone: ['', [Validators.minLength(9), Validators.maxLength(10)]],
      phone: ['', [Validators.minLength(9), Validators.maxLength(10)]],
      gender: ['', Validators.required],
      birthDate: ['', Validators.required],
      serviceName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
    },
      {
        validator: this.utilsService.CheckPassword('password', 'confirmPassword'),
    });
  }

  get name() {
    return this.ClientForm.get('name');
  }

  get nif() {
    return this.ClientForm.get('nif');
  }

  get email() {
    return this.ClientForm.get('email');
  }

  get password() {
    return this.ClientForm.get('password');
  }

  get confirmPassword() {
    return this.ClientForm.get('confirmPassword');
  }

 get phone() {
    return this.ClientForm.get('phone');
  }

  get f() {
    return this.ClientForm.controls;
  }

  onSubmit() {



    this.submitted = true;

    // reset alerts on submit
    this.alertService.clear();

    // stop here if form is invalid
    if (this.ClientForm.invalid) {
      return;
    }

    // user to JSON

      this.clientToJSON = JSON.parse(JSON.stringify(this.ClientForm.value));
      this.clientService.addClient(this.clientToJSON)
        .pipe(first())
          .subscribe(
            success => {
              this.alertService.success(success.message);
              // setTimeout(() => { this.router.navigate(['/clientTable']); }, 1500);
            },
            error => {
              this.alertService.error(JSON.parse(JSON.stringify(error)));
              this.isLoading = false;
      });
    }
  }



