import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RegistrationComponent } from './registration/registration.component';
import { HomeComponent } from './home/home.component';
import { TableClientsComponent } from './table-clients/table-clients.component';
import { ClientComponent } from './client/client.component';
import { AdministratorComponent } from './administrator/administrator.component';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ServicesTableComponent } from './services/services-table/services-table.component';
import { CreateServiceComponent } from './services/create-service/create-service.component';
import { CreateEmployeeComponent } from './employees/create-employee/create-employee.component';
import { EmployeesTableComponent } from './employees/employees-table/employees-table.component';
import { ClientService } from './client.service';
import { FilterPipe} from './filters/filter.pipe';
import { AlertComponent } from './alerts/alert.component';
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { ErrorInterceptor } from './interceptors/error.interceptor';
import { AuthGuard } from './auth/auth.guard';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import {NgxPaginationModule} from 'ngx-pagination';
import { Globals } from './globals';
import { LoadingSpinnerComponent } from './loading/loading-spinner.component';






const appRoutes: Routes =  [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'client/:clientId', component: ClientComponent, canActivate: [AuthGuard]},
  { path: 'registration', component: RegistrationComponent },
  { path: 'clientsTable', component: TableClientsComponent, canActivate: [AuthGuard] },
  { path: 'administrator', component: AdministratorComponent },
  { path: 'servicesTable', component: ServicesTableComponent, canActivate: [AuthGuard] },
  { path: 'createService', component: CreateServiceComponent },
  { path: 'createEmployee', component: CreateEmployeeComponent },
  { path: 'employeesTable', component: EmployeesTableComponent }
  
   
];


@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    HomeComponent,
    TableClientsComponent,
    AdministratorComponent,
    ClientComponent,
    ServicesTableComponent,
    CreateServiceComponent,
    CreateEmployeeComponent,
    EmployeesTableComponent,
    AlertComponent,
    FilterPipe,
    LoadingSpinnerComponent,
 
  ],

  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    NgbModalModule.forRoot(),
    NgbModule,
    ReactiveFormsModule,
    NgxPaginationModule
  ],
  
  providers: [
    NgbModalConfig,
    NgbModal, 
    NgModule, 
    NgbModule, 
    NgbModalModule, 
    ClientService, 
    AuthGuard,
    NgbActiveModal,
    Globals,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],

  bootstrap: [AppComponent, HomeComponent],
  exports: [ HomeComponent ]
})
export class AppModule { }
