import { Client } from './client.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';




const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

@Injectable({
    providedIn: 'root'
})

export class ClientService {
    constructor(private http: HttpClient) { }



API = 'http://localhost:8080';

  getAll() {
    return this.http.get<Client[]>(this.API + '/clientsTable')
  }

  getById(clientId: number) {
    return this.http.get<Client>(this.API + '/client/' + clientId);
  }

  addClient(client: string) {
    return this.http.post<any>(this.API + '/registration', client);
  }

  updateClient(clientId: number, client: string) {
    return this.http.put<any>(this.API + '/client/' + clientId, client);
  }

 
}

