import { Component, OnInit, Input } from '@angular/core';
import { Employee } from 'src/app/employee.model';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from 'src/app/alert.service';
import { first } from 'rxjs/operators';
import { EmployeesService } from 'src/app/employees.service';

@Component({
  selector: 'app-employees-table',
  templateUrl: './employees-table.component.html',
  styleUrls: ['./employees-table.component.css']
})
export class EmployeesTableComponent implements OnInit {
  @Input() employeeId: number;
  employees: Employee[] = [];
  success = '';
  message: string;
  
  constructor(private employeesService: EmployeesService, private alertService: AlertService, private router: Router, private route: ActivatedRoute) { }

  fetchEmployees() {
    this.employeesService.getAll().pipe(first()).subscribe(employees => {
      this.employees = employees;
     });

  }

  onDeleteEmployee(employeeId : number) {
    this.alertService.clear();
    let alert = confirm('Tem a certeza que deseja eliminar o colaborador?');
    if (alert) {
      this.employeesService.deleteEmployee(employeeId).subscribe(success => {
        this.alertService.success(success.message);
        // setTimeout(() => { this.alertService.clear(); }, 2000);
        this.fetchEmployees();
      },
        error => {
          this.alertService.error(error);
          console.log(error.message);
          this.fetchEmployees();
        });
    } 
  }

  ngOnInit() {
    this.fetchEmployees();
  }

}



  



