import { Component, OnInit } from '@angular/core';
import {Http, Response, Headers, ResponseContentType, RequestOptions} from '@angular/http';
import {HttpSecService} from "../_service/util/http-sec.service";

import {Observable} from "rxjs";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise'; //bez tego co jakis czas nie dziala http.post powodujac przeladowanie strony
import 'rxjs/Rx' ;

import {AddEventClass} from "./addEventClass";
import {City, EventType, Region} from "../_model/domainClass";
import {Route, Router, Data} from "@angular/router";
import {FormGroup, FormBuilder, FormControl, Validators} from "@angular/forms";
import {findIndex} from "rxjs/operator/findIndex";

@Component({
  selector: 'app-event-create',
  templateUrl: './event-create.component.html',
  styleUrls: ['./event-create.component.css']
})
export class EventCreateComponent implements OnInit {

  regions: Region[];
  eventTypes: EventType[];
  cities: City[];
  addEventClass: AddEventClass;

  complexForm: FormGroup;
  isMapValid: number;
  isDateValid: number;

  dataRozpoczeciaPomoc: Date;
  dataZakonczeniaPomoc: Date;

  constructor(private http: Http, private myHttp: HttpSecService, private router: Router, public fb: FormBuilder) {
    this.complexForm = fb.group({
      'title':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(100)])),
      'startTime':new FormControl(null, Validators.compose([Validators.required])),
      'endTime':new FormControl(null, Validators.compose([Validators.required])),
      'description':new FormControl(null, Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(1000)])),
      'streetName':new FormControl(null, Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(50)])),
      'streetNo':new FormControl(null, Validators.compose([Validators.required, Validators.minLength(1), Validators.maxLength(20)])),
      'geoLength':new FormControl(null, Validators.compose([Validators.required]))
    })

    this.addEventClass = new AddEventClass();
    this.isDateValid = -1;
    this.isMapValid = -1;
  }

  ngOnInit() {
    this.getEventRegions();
    this.getEventTypes();
  }

  getEventRegions() {
    console.info("Pobieranie regionow");
    this.http.get(this.myHttp.getUrl() + '/api/util/dictionary/regions').subscribe((data: Response)=> this.regions = data.json());
  }

  getEventTypes() {
    console.info("Pobieranie eventTypes");
    this.http.get(this.myHttp.getUrl() + '/api/util/dictionary/eventTypes').subscribe((data: Response)=> this.eventTypes = data.json());
  }

  getEventCities(region: Region) {
    console.info("Pobieranie nazw miast");
    if (region!=null)
     this.http.get(this.myHttp.getUrl() + '/api/util/dictionary/cities?idRegion='+region.idRegion).subscribe((data: Response)=> this.cities = data.json());
  }

  validDates() {
    if(this.dataZakonczeniaPomoc > this.dataRozpoczeciaPomoc) this.isDateValid = 1;
    else this.isDateValid = 0;
  }

  validMap() {
    if (this.addEventClass.geoLength) this.isMapValid = 1;
    else this.isMapValid = 0;
  }


  postAddEvent(){
    console.log("sprawdzenme");
    this.validDates();
    this.validMap();
    if(this.isDateValid==1 && this.isMapValid==1) {
      this.addEventClass.startTime = this.dataRozpoczeciaPomoc.toString();
      this.addEventClass.endTime = this.dataZakonczeniaPomoc.toString();
console.log("walidacja !")
      return this.http.post(this.myHttp.getUrl()+'/api/event/dashboard/create',this.addEventClass,this.myHttp.postConfig())
        .subscribe((data: Response)=> this.router.navigate(['/event/view/'+data.text()]));
    }
  }

  /* MAPA */
  zoom: number = 12;
  lat: number = 52.25353;
  lng: number = 20.90067;

  clickedMarker(label: string, index: number) {
    console.log(`clicked the marker: ${label || index}`)
  }

  mapClicked($event: any) {
    this.markers.pop();
    this.markers.push({
      lat: $event.coords.lat,
      lng: $event.coords.lng,
      draggable: true
    });
    this.isMapValid = 1;
    this.addEventClass.geoLength=this.markers[0].lng.toString();
    this.addEventClass.geoWidth=this.markers[0].lat.toString();
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
