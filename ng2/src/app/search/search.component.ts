import {Component, OnInit, ViewChild} from '@angular/core';
import {SebmGoogleMap} from "angular2-google-maps/core";
import {EventSearchForm, EventSearchResult} from "../_model/searchDomain";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpSecService} from "../_service/util/http-sec.service";
import {CustomDateService} from "../_service/util/custom-date.service";
import {Http,Response} from "@angular/http";
import {City, EventType, Region} from "../_model/domainClass";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  constructor(private http: Http, private myHttp: HttpSecService, public fb: FormBuilder, public dateService: CustomDateService) {
    this.complexForm = fb.group({
      'textContent':new FormControl(null,Validators.compose([Validators.maxLength(100)])),
      'region':new FormControl(null),
      'city':new FormControl(null),
      'dateFrom':new FormControl(null),
      'dateTo':new FormControl(null),
      'fromGeoWidth':new FormControl(null),
      'toGeoWidth':new FormControl(null),
      'fromGeoLenght':new FormControl(null),
      'toGeoLenght':new FormControl(null),
      'freeEntrance':new FormControl(null, Validators.compose([Validators.required])),
      'registerEnabled':new FormControl(null)
    })
    this.eventSearchForm=new EventSearchForm;

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


  /*FORMULARZ*/
  public eventSearchForm:EventSearchForm;
  public complexForm: FormGroup;
  public regions: Region[];
  public cities: City[];
  public eventTypes: EventType[];
  public dataRozpoczeciaPomoc: Date;
  public dataZakonczeniaPomoc: Date;


  /*WYNIKI WYSZUKIWANIA*/
  public eventSearchResult:EventSearchResult[];


  /*SLIDER Otwieranie i zamykanie slidera mapy*/
  public isSlideHide = true;

  public changeSlideStatus(){
    if(this.isSlideHide){
      (<HTMLScriptElement>document.getElementById("mySideMap")).style.width = "80%"; //pokaz

      (<HTMLScriptElement>document.getElementById("mapPannel")).style.height = "70%"; //pokaz
      (<HTMLScriptElement>document.getElementById("mySideMapTitle")).style.height = "30%"; //pokaz

      // this.isSlideHide=false;
    }
    else{
      (<HTMLScriptElement>document.getElementById("mySideMap")).style.width = "8%";//ukryj

      (<HTMLScriptElement>document.getElementById("mapPannel")).style.height = "100%"; //pokaz
      (<HTMLScriptElement>document.getElementById("mySideMapTitle")).style.height = "0"; //pokaz

      // this.isSlideHide=true;
    }
    setTimeout(() => this.map.triggerResize(), 600);
    this.changeFlag(this.isSlideHide);
  }

  private changeFlag(flag: boolean){
    if(flag) //byl schowany
      setTimeout(() => this.isSlideHide=false, 300);
    else //byl widoczny
      setTimeout(() => this.isSlideHide=true, 50);
  }
  /*END Otwieranie-zamykanie slidera mapy */

  /* Dzialanie na mapie*/
  private kilometersRange:number = 51;
  private kmToDeg:number=0.0089827083;

  public changeRange(value: number){
    console.info("Zmiana wartosci na: "+value);
    this.kilometersRange=value;
  }
  /*End dzialanie na mapie*/


  /* MAPA */
  @ViewChild(SebmGoogleMap) map: SebmGoogleMap;

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

