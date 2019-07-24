import { Component, OnInit, Input } from '@angular/core';
import { Client } from '../client.model';
import { ClientService } from '../client.service';
import { first } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from '../alert.service';



const options = {
    fieldSeparator: ',',
    quoteStrings: '"',
    decimalSeparator: '.',
    showLabels: true,
    showTitle: true,
    useBom: true,
    useKeysAsHeaders: true,
};

@Component({
    selector: 'app-table-clients',
    templateUrl: './table-clients.component.html',
    styleUrls: ['./table-clients.component.css']
})

export class TableClientsComponent implements OnInit { 
@Input() clientId: number;
// isLoading = true;
searchText: string;
clients: Client [] = []; 
filteredClients: Client[] = [];
bothFilteredClients: Client[] = [];


constructor(private clientService: ClientService, private alertService: AlertService, private router: Router, private route: ActivatedRoute) { }


filter = { fraudulent: false, status: false};

  filterChange() {
    this.filteredClients = this.clients.filter(x => 
       (x.fraudulent === true && this.filter.fraudulent)
       || (x.status === true && this.filter.status)
    );

    this.bothFilteredClients = this.clients.filter(x => 
      (x.fraudulent === true && this.filter.fraudulent)
      && (x.status === true && this.filter.status)
   );

  }

fetchClients() {
    this.clientService.getAll().pipe(first()).subscribe(clients => {
    this.clients = clients;
     });
  }

  ngOnInit() {
    this.fetchClients();
  }


}
