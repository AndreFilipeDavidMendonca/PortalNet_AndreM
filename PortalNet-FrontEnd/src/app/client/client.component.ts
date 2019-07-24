import { Component, OnInit,Input, EventEmitter, Output, Injectable } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Client } from '../client.model';
import { ClientService } from '../client.service';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { AlertService } from '../alert.service';
import { ServicesService } from '../services.service';
import { Service } from '../service.model';
import { AuthenticationService } from '../authentication.service';
import { User } from '../user.model';




@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {
  @Output() passEntry: EventEmitter<any> = new EventEmitter();
  @Input() public client: Client;
  @Input() clientId: number;
  services: Service[] = [];
  placeHolder: string;
  clientToJSON: string;
  submitted = false;
  filteredServices: Service[] = [];
  currentUser: User;
  
  constructor(private servicesService: ServicesService, private authenticationService: AuthenticationService, private modalService: NgbModal, private clientService: ClientService, private router: Router, private route: ActivatedRoute, private alertService: AlertService) {
    this.currentUser = this.authenticationService.currentUserValue;
  }
  
  fetchServices() {
    this.servicesService.getAll().pipe(first()).subscribe(services => {
      this.services = services;
      this.filteredServices = this.services.filter(x =>  (x.status === true));
     });

  }

  openBackDropCustomClass(content) {
    this.modalService.open(content, {backdropClass: 'light-blue-backdrop'});
    
  }

  openModalAddress(address) {
    this.modalService.open(address, { windowClass: 'dark-modal' });
    this.placeHolder = 'Introduza a nova Morada!'
  }

  openModalPostalCode(postalCode) {
    this.modalService.open(postalCode, { windowClass: 'dark-modal' });
    this.placeHolder = 'Introduza o novo Código-Postal!'
  }

  openModalEmail(email) {
    this.modalService.open(email, { windowClass: 'dark-modal' });
    this.placeHolder = 'Introduza o novo E-Mail!'
  }

  openModalMobilePhone(mobilePhone) {
    this.modalService.open(mobilePhone, { windowClass: 'dark-modal' });
    this.placeHolder = 'Introduza o novo número de Telemóvel!'
  }

  openModalLocality(city) {
    this.modalService.open(city, { windowClass: 'dark-modal' });
    this.placeHolder = 'Introduza a nova Localidade!'
  }
  openModalService(service) {
    this.modalService.open(service, { windowClass: 'dark-modal' });
  }
  

  openModalStatus(status) {
    this.modalService.open(status, { windowClass: 'dark-modal' });
  }

  openModalPhone(phone) {
    this.modalService.open(phone, { windowClass: 'dark-modal' });
    this.placeHolder = 'Introduza o novo número de telefone!'
  }

  openModalFraudulent(fraudulent) {
    this.modalService.open(fraudulent, { windowClass: 'dark-modal' });
  }

  openModalAssociateService(ASModal) {
    this.modalService.open(ASModal, { windowClass: 'dark-modal' });
  }
  
  

  openSm(content) {
    this.modalService.open(content, { size: 'sm' });
  }

  openLg(content) {
    this.modalService.open(content, { size: 'lg' });
  }

  openVerticallyCentered(content) {
    this.modalService.open(content, { centered: true });
  }

 
  fetchClientById(){  
    this.clientService.getById(this.currentUser.userId)
      .pipe(first())
      .subscribe(client => {
        this.client = client[0];
      });
  }

  ngOnInit() {
    this.fetchServices();
    this.route.paramMap.subscribe(data => {
    this.clientId = +data.get('clientId');
    this.fetchClientById();
    });  
  }

  
  
  saveChanges() {
    this.passEntry.emit(this.client);
  }

 
  
  onSubmit() {
    this.submitted = true;

    // reset alerts on submit
    this.alertService.clear();


    // user to JSON

      this.clientToJSON = JSON.parse(JSON.stringify(this.client));
      this.clientService.updateClient(this.clientId, this.clientToJSON)
        .pipe(first())
          .subscribe(
            success => {
              this.alertService.success(success.message);
              // setTimeout(() => { this.router.navigate(['/clientTable']); }, 1500);
            },
            error => {
              this.alertService.error(JSON.parse(JSON.stringify(error)));
      });
  }
 }
   
 

  


