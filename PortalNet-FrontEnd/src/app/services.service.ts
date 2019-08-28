import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Service } from './service.model';


const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

@Injectable({
    providedIn: 'root'
})

export class ServicesService {
      
    constructor(private http: HttpClient) { }



API = 'http://localhost:9000';

  getAll() {
    return this.http.get<Service[]>(this.API + '/servicesTable')
  }

  getHomeServices() {
    return this.http.get<Service[]>(this.API + '/home')
  }

  getById(serviceID: number) {
    return this.http.get<Service>(this.API + '/registration' + serviceID);
  }

  getServiceById(serviceID: number) {
    return this.http.get<Service>(this.API + '/servicesTable/' + serviceID);
  }

  getByName(name: string) {
    return this.http.get<Service>(this.API + '/registration/' + name);
  }

  addService(service: string) {
    return this.http.post<any>(this.API + '/createService', service);
  }

  updateService(service: Service) {
    return this.http.put<any>(this.API + '/servicesTable', service);
  }
}

