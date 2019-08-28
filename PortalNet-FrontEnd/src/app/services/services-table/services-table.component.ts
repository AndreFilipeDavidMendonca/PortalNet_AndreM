import { Component, OnInit, Input, Injectable } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Service } from 'src/app/service.model';
import { first } from 'rxjs/operators';
import { AlertService } from 'src/app/alert.service';
import { ServicesService } from 'src/app/services.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from 'src/app/user.model';
import { AuthenticationService } from 'src/app/authentication.service';
import { AppComponent } from 'src/app/app.component';




@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-services-table',
  templateUrl: './services-table.component.html',
  styleUrls: ['./services-table.component.css']
})
export class ServicesTableComponent implements OnInit {
  @Input() serviceID: number;
  service: Service;
  serviceToJSON: string;
  submitted = false;
  services: Service[] = [];
  success = '';
  message: string;
  currentUser: User;

  constructor(private servicesService: ServicesService, 
    private appComponent: AppComponent, 
    private authenticationService: AuthenticationService, 
    private modalService: NgbModal, 
    private alertService: AlertService, 
    private router: Router, 
    private route: ActivatedRoute) {

    this.currentUser = this.authenticationService.currentUserValue;
   }


  ngOnInit() {
    this.fetchServices();
  }

  fetchServices() {
    this.servicesService.getAll().pipe(first()).subscribe(services => {
      this.services = services;
     });

  }

  
  openBackDropCustomClass(content) {
    this.modalService.open(content, {backdropClass: 'light-blue-backdrop'});
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

  openModalService(content) {
    this.modalService.open(content, { windowClass: 'dark-modal' });
  }

  fetchServiceById(serviceID: number){
    this.servicesService.getServiceById(serviceID)
      .pipe(first())
      .subscribe(service => {
        this.service = service; 
      });
  }

  onUpdateService(service: Service) {
    this.submitted = true;

    // reset alerts on submit
    this.alertService.clear();


    // user to JSON

      // this.serviceToJSON = JSON.parse(JSON.stringify(this.service));
      this.servicesService.updateService(this.service)
        .pipe(first())
          .subscribe(
            success => {
              this.alertService.success(success.message);
              this.fetchServices();
            },
            error => {
              this.alertService.error(JSON.parse(JSON.stringify(error)));
              console.log(error.message);
      });
  }
}

 



