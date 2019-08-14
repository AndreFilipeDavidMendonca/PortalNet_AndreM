import { Client } from './client.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AssociatedService } from './associatedService.model';


const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

@Injectable({
    providedIn: 'root'
})

export class AssociatedServiceService {
    constructor(private http: HttpClient) { }



API = 'http://localhost:8080';

  getAsServices(clientId: number) {
    return this.http.get<AssociatedService[]>(this.API + '/client/asServices/' + clientId);
  }


  addAsService(asService: string) {
    return this.http.post<any>(this.API + '/client/registerAsServices', asService);
  }

  updateAsService(id: number, associatedService: string) {
    return this.http.put<any>(this.API + '/client/' + id, associatedService);
  }

  deleteAsService(associatedServiceID: number) {
    return this.http.delete<any>(this.API + '/client/deleteAsService/' + associatedServiceID);
  }

}

