import { Component } from '@angular/core';
import {Client} from "../models/client";
import {ClientService} from "../service/client.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  client: Client;


  constructor(private clientService: ClientService) {
    this.clientService.client.subscribe(x => this.client = x);
  }

  logout() {
    this.clientService.logout();
  }
}
