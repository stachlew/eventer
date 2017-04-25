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
import {City, EventType, Region} from "../_model/domainClass";

@Component({
  selector: 'app-event-create',
  templateUrl: './event-create.component.html',
  styleUrls: ['./event-create.component.css']
})
export class EventCreateComponent implements OnInit {

  customers : Customer[];
  customersAdmin: Customer[];
  regions: Region[];
  eventTypes: EventType[];
  cityNames: City[];
  addEventClass: AddEventClass;

  demoResponse: string = "nie wywolano pobrania odpowiedzi"

  imageUrl: string = this.myHttp.getUrl()+ "/api/getImage";

  constructor(private http: Http, private myHttp: HttpSecService/*API*/) {
    this.addEventClass = new AddEventClass();
  }

  ngOnInit() {
    this.getEventRegions();
    this.getEventTypes();
  }

  //Wyslanie zadania na serwer i oczekiwanie na zwrot danych dla goscia
  getEventRegions() {
    console.info("Pobieranie regionow");
    this.http.get(this.myHttp.getUrl() + '/api/util/dictionary/regions').subscribe((data: Response)=> this.regions = data.json());
  }

  getEventTypes() {
    console.info("Pobieranie eventTypes");
    this.http.get(this.myHttp.getUrl() + '/api/util/dictionary/eventTypes').subscribe((data: Response)=> this.eventTypes = data.json());
  }
  //TODO: napisać wyszukiwanie po parametrze regionu!
  getEventCities() {
    console.info("Pobieranie nazw miast");
    this.http.get(this.myHttp.getUrl() + '/api/util/dictionary/cities').subscribe((data: Response)=> this.cityNames = data.json());
  }

//Wyslanie zadania na serwer i zapisanie zwroconych danych zaleznych od typu uzytkownika
  postAddEvent(){
    // postEventDashboardCreate
    return this.http.post(this.myHttp.getUrl()+'/api/postTest',this.addEventClass,this.myHttp.postConfig())
      .subscribe((data: Response)=> this.demoResponse = data.text()); //Zwykly string wiec .text
    // .subscribe((data: Response)=> this.demoResponse = data.json()); GDYBY OBIEKT
  }

  /* MAPA */
  zoom: number = 12;
  lat: number = 52.25353;
  lng: number = 20.90067;

  clickedMarker(label: string, index: number) {
    console.log(`clicked the marker: ${label || index}`)
  }

  mapClicked($event: any) {
    this.markers.push({
      lat: $event.coords.lat,
      lng: $event.coords.lng,
      draggable: true
    });
    console.info("sze/dlu "+$event.coords.lat+" "+$event.coords.lng);
  }

  markerDragEnd(m: marker, $event: any) {
    m.lat = $event.coords.lat;
    m.lng = $event.coords.lng;
    console.log('dragEnd', m, $event);
  }

  markers: marker[] = [
  ]
  /* END: MAPA */

}

/*DO MAPY*/
interface marker {
  lat: number;
  lng: number;
  label?: string;
  draggable: boolean;
}
