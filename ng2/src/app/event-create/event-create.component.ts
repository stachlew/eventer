import { Component, OnInit } from '@angular/core';
import {Http, Response, Headers, ResponseContentType, RequestOptions} from '@angular/http';
import {HttpSecService} from "../_service/util/http-sec.service";

import {Observable} from "rxjs";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise'; //bez tego co jakis czas nie dziala http.post powodujac przeladowanie strony
import 'rxjs/Rx' ;

import {Customer} from "./customer";
import {AddEventClass} from "./addEventClass";

import { FileUploader } from 'ng2-file-upload'; //UPLOAD PLIKU
import {SafeResourceUrl, DomSanitizer} from "@angular/platform-browser"; //iFRAME

@Component({
  selector: 'app-event-create',
  templateUrl: './event-create.component.html',
  styleUrls: ['./event-create.component.css']
})
export class EventCreateComponent implements OnInit {

  customers : Customer[];
  customersAdmin: Customer[];
  addEventClass: AddEventClass;

  demoResponse: string = "nie wywolano pobrania odpowiedzi"

  imageUrl: string = this.myHttp.getUrl()+ "/api/getImage";

  constructor(private http: Http, private myHttp: HttpSecService/*API*/) {
    this.addEventClass = new AddEventClass();
  }

  ngOnInit() { }

// Wysylanie danych na serwer z autoryzacja i bez sprowadzone do jednej postConfig() z wbudowanymi parametrami. Dostepu broni znacznik metody na serwerze
  postGuest() {
    console.info("postGuest(): "+ this.addEventClass.nazwaWydarzenia + " "+ this.addEventClass.napis);
    return this.http.post(this.myHttp.getUrl()+'/api/postGuest',this.addEventClass,this.myHttp.postConfig()).toPromise();
  }

  postAdmin() {
    console.info("postAdmin(): "+ this.addEventClass.nazwaWydarzenia + " "+ this.addEventClass.napis);
    return this.http.post(this.myHttp.getUrl()+'/api/postAdmin',this.addEventClass,this.myHttp.postConfig()).toPromise();
  }

  // //Wyslanie zadania na serwer przez goscia. Brak oczekiwania na dane zwrotne
  // clickedGuest() {
  //   console.info("Przycisk gosc");
  //   this.http.get(this.myHttp.getUrl()+'/api/getGuest').subscribe((data :Response)=> console.log(data));
  // }
  //
  // //Wyslanie zadania na serwer przez usera i admina (autoryzacja). Brak oczekiwania na dane zwrotne
  // clickedUser() {
  //   console.info("Przycisk user");
  //   this.http.get(this.myHttp.getUrl()+'/api/getUser',this.myHttp.getConfig()).subscribe();
  // }
  //
  // clickedAdmin() {
  //   console.info("Przycisk admin");
  //   this.http.get(this.myHttp.getUrl()+'/api/getAdmin',this.myHttp.getConfig()).subscribe();
  // }

}
