import { Component, OnInit } from '@angular/core';
import {Http, Response, Headers, ResponseContentType, RequestOptions} from '@angular/http';
import {HttpSecService} from "../_service/util/http-sec.service";

import {Observable} from "rxjs";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise'; //bez tego co jakis czas nie dziala http.post powodujac przeladowanie strony
import 'rxjs/Rx' ;

import {Customer} from "./customer";
import {DemoClass} from "./demoClass";

import { FileUploader } from 'ng2-file-upload'; //UPLOAD PLIKU
import {SafeResourceUrl, DomSanitizer} from "@angular/platform-browser"; //iFRAME


@Component({
  selector: 'app-demo',
  templateUrl: './demo.component.html',
  styleUrls: ['./demo.component.css']
})
export class DemoComponent implements OnInit {

  customers : Customer[];
  customersAdmin: Customer[];
  demoClass: DemoClass;

  imageUrl: string = this.myHttp.getUrl()+ "/api/getImage";

  constructor(private http: Http, private myHttp: HttpSecService/*API*/, sanitizer: DomSanitizer/*iFRAME*/) {
    this.demoClass = new DemoClass();

    this.url = sanitizer.bypassSecurityTrustResourceUrl(this.ytUrl+this.videoUrl); //iFrame
  }

  /*DO iFRAME*/
  ytUrl="http://www.youtube.com/embed/";
  videoUrl = "yyQ3YCbmcUU";
  url: SafeResourceUrl;
  /*END: iFRAME*/

  ngOnInit() {  }

  // Wysylanie danych na serwer z autoryzacja i bez sprowadzone do jednej postConfig() z wbudowanymi parametrami. Dostepu broni znacznik metody na serwerze
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

  //Metoda pobierania pliku docx z serwera. Par1: adres Par2: domyslna nazwa dla pobieranego pliku
  downloadFile() {
    this.myHttp.getDOCXFromApi('api/download',"dokument_test");
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
    {
      lat: 52.238907,
      lng: 20.944372,
      label: 'K',
      draggable: true
    },
    {
      lat: 52.229892,
      lng: 20.896103,
      label: 'P',
      draggable: false
    },
    {
      lat: 52.239127,
      lng: 20.897972,
      label: 'M',
      draggable: true
    }
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

