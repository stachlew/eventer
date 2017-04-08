import { Component, OnInit } from '@angular/core';
import {Http, Response} from '@angular/http';
import {HttpSecService} from "../_service/util/http-sec.service";

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise'; //bez tego co jakis czas nie dziala http.post powodujac przeladowanie strony

import {Customer} from "./customer";
import {DemoClass} from "./demoClass";

import { FileUploader } from 'ng2-file-upload';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  customers : Customer[];
  customersAdmin: Customer[];
  demoClass: DemoClass;

  constructor(private http: Http, private myHttp: HttpSecService) {
    this.demoClass = new DemoClass();
  }

  ngOnInit() {  }

  //Wysylanie danych na serwer z autoryzacja i bez sprowadzone do jednej postConfig() z wbudowanymi parametrami. Dostepu broni znacznik metody na serwerze
  postGuest() {
    console.info("postGuest(): "+ this.demoClass.nr + " "+ this.demoClass.napis);
    return this.http.post(this.myHttp.getUrl()+'/api/postGuest',this.demoClass,this.myHttp.postConfig()).toPromise();
  }

  postAdmin() {
    console.info("postAdmin(): "+ this.demoClass.nr + " "+ this.demoClass.napis);
    return this.http.post(this.myHttp.getUrl()+'/api/postAdmin',this.demoClass,this.myHttp.postConfig()).toPromise();
  }


  //Wyslanie zadania na serwer przez goscia. Brak oczekiwania na dane zwrotne
  clickedGuest() {
    console.info("Przycisk gosc");
    this.http.get(this.myHttp.getUrl()+'/api/getGuest').subscribe((data :Response)=> console.log(data));
  }

  //Wyslanie zadania na serwer przez usera i admina (autoryzacja). Brak oczekiwania na dane zwrotne
  clickedUser() {
    console.info("Przycisk user");
    this.http.get(this.myHttp.getUrl()+'/api/getUser',this.myHttp.getConfig()).subscribe();
  }

  clickedAdmin() {
    console.info("Przycisk admin");
    this.http.get(this.myHttp.getUrl()+'/api/getAdmin',this.myHttp.getConfig()).subscribe();
  }

  //Wyslanie zadania na serwer i oczekiwanie na zwrot danych dla goscia
  getCustomers() {
    console.info("Pobieranie customerow przez gosci");
    this.http.get(this.myHttp.getUrl() + '/api/getGuest').subscribe((data: Response)=> this.customers = data.json());
  }

  //Wyslanie zadania na serwer i zapisanie zwroconych danych dla zalogowanego uzytkownika (tu admina - adnotacja metody na serwerze)
  getCustomersAdmin() {
    console.info("Pobieranie customerow przez admina");
    this.http.get(this.myHttp.getUrl() + '/api/getAdmin',this.myHttp.getConfig()).subscribe((data: Response)=> this.customersAdmin = data.json());
  }

  //wystarczy tyle by uploadowac plik. Nalezy odpowiednio przystroic formularz tak jak to jest na przykladzie
  public uploader:FileUploader = new FileUploader({url:this.myHttp.getUrl() + 'api/postFile',authToken:this.myHttp.getToken()});

}
