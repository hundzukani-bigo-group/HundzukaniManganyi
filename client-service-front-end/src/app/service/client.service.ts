import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Client} from "../models/client";
import {BehaviorSubject, map, Observable} from "rxjs";
import {Router} from "@angular/router";

@Injectable({ providedIn: 'root' })
export class ClientService {

  private httpOptions = {
    withCredentials: false
  };

  private clientSubject: BehaviorSubject<Client>;
  public client: Observable<Client>;

  constructor(
    private router: Router,
    private http: HttpClient
  ) {
    this.clientSubject = new BehaviorSubject<Client>(JSON.parse(localStorage.getItem('client')  || '{}'));
    this.client = this.clientSubject.asObservable();
  }

  public get userValue(): Client | null {
    const foundClient = localStorage.getItem("client");
    if (foundClient) {
      return JSON.parse(foundClient);
    }
    return null;
  }

  login(username: String, password: String) {
    return this.http.post<Client>(`http://localhost:8080/client/authenticate`, { username, password })
      .pipe(map(client => {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        localStorage.setItem('client', JSON.stringify(client));
        this.clientSubject.next(client);
        return client;
      }));
  }

  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem('client');
    console.log('Current User Value ' + JSON.stringify(this.userValue));
    this.router.navigate(['/login']);
  }

  /** POST: add a new client or user to the backend system */
  register(client: Client) {
     return this.http.post<Client>('//localhost:8080/client/create', client, this.httpOptions);
  }

}
