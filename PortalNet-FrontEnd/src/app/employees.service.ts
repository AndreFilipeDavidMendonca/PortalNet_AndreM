import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from './employee.model';


const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

@Injectable({
    providedIn: 'root'
})

export class EmployeesService {
      
    constructor(private http: HttpClient) { }



API = 'http://localhost:8080';

  getAll() {
    return this.http.get<Employee[]>(this.API + '/employeesTable')
  }

  getById(employeeId: number) {
    return this.http.get<Employee>(this.API + '/employeesTable');
  }

  addEmployee(employee: string) {
    return this.http.post<any>(this.API + '/createEmployee', employee);
  }

  deleteEmployee(employeeId: number) {
    return this.http.delete<any>(this.API + '/employeesTable/' + employeeId);
  }
}

