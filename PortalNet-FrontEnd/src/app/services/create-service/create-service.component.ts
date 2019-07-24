import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from 'src/app/alert.service';
import { ServicesService } from 'src/app/services.service';
import { Service } from 'src/app/service.model';
import { first } from 'rxjs/operators';


@Component({
  selector: 'app-create-service',
  templateUrl: './create-service.component.html',
  styleUrls: ['./create-service.component.css']
})
export class CreateServiceComponent implements OnInit {
  service: Service;
  submitted = false;
  ServiceForm: FormGroup;
  serviceToJSON: string;
  isLoading = false;
  error = '';
  success = '';
  message: string;
  
  constructor(private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private alertService: AlertService,
    private servicesService: ServicesService) { }
  
  
  
  onReset() {
    this.ServiceForm.reset();
  }

  ngOnInit() {
    this.ServiceForm = this.formBuilder.group({
      name: ['', Validators.required],
      internet: ['', Validators.required],
      tv: ['', Validators.required],
      mobilePhone: ['', Validators.required],
      phone: ['', Validators.required],
      loyalty: ['', Validators.required],
      price: ['', Validators.required],
      imgName: ['', Validators.required]
    });
  
  }


  onSubmit() {
      this.submitted = true;
      
      // reset alerts on submit
      this.alertService.clear();
      
      // stop here if form is invalid
      if (this.ServiceForm.invalid) {
        return;
      }
      
      // user to JSON
      
        this.serviceToJSON = JSON.parse(JSON.stringify(this.ServiceForm.value));
        this.servicesService.addService(this.serviceToJSON)
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





