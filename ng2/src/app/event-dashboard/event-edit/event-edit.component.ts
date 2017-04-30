import { Component, OnInit } from '@angular/core';
import {EventStorageService} from "../event-storage.service";
import {HttpSecService} from "../../_service/util/http-sec.service";
import {Http, Response} from "@angular/http";
import {EventDashboardInfo} from "../../_model/dashboardClass";
import {City, EventType, Place, Region} from "../../_model/domainClass";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomDateService} from "../../_service/util/custom-date.service";
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";


@Component({
  selector: 'app-event-edit',
  templateUrl: './event-edit.component.html',
  styleUrls: ['./event-edit.component.css']
})
export class EventEditComponent implements OnInit {

  public isLoading=false;

  constructor(private eveStore: EventStorageService, private http: Http, public fb: FormBuilder, public dateService: CustomDateService, private myHttp: HttpSecService,sanitizer: DomSanitizer) {
    this.isLoading=true;
    this.sanitizer=sanitizer;
    this.idEvent=this.eveStore.getCurrentEventId();
    this.eventInfo=new EventDashboardInfo;
    this.complexForm = fb.group({
      'title':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(100)])),
      'startTime':new FormControl(null, Validators.compose([Validators.required])),
      'endTime':new FormControl(null, Validators.compose([Validators.required])),
      'description':new FormControl(null, Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(1000)])),
      'streetName':new FormControl(null, Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(50)])),
      'streetNo':new FormControl(null, Validators.compose([Validators.required, Validators.minLength(1), Validators.maxLength(20)])),
      'geoLength':new FormControl(null, Validators.compose([Validators.required]))
    });
    this.isDateValid = -1;
    this.isMapValid = -1;
    this.isCityValid = -1;
    this.isCapacityValid = -1;


    this.http.get(this.myHttp.getUrl() + '/api/event/dashboard/edit/getEventInfo/'+this.idEvent,this.myHttp.getConfig()).subscribe((data: Response)=> {this.eventInfo = data.json(),this.updateData()});
    this.getEventRegions();
    this.getEventTypes();

    //ZAMIESZANIE Z LISTA WYBORU d
    this.evePlace=new Place;
    this.eveCity=new City;
    this.eveRegion=new Region;
    this.eveCity.region=this.eveRegion;
    this.evePlace.city=this.eveCity;

    //this.eventInfo=this.eveStore.getEvent();
  }

  private downloadData():void{
    this.isLoading=true;
    this.http.get(this.myHttp.getUrl() + '/api/event/dashboard/edit/getEventInfo/'+this.idEvent,this.myHttp.getConfig()).subscribe((data: Response)=> {this.eventInfo = data.json(),this.updateData()});
    this.getEventRegions();
    this.getEventTypes();

    //ZAMIESZANIE Z LISTA WYBORU
    this.evePlace=new Place;
    this.eveCity=new City;
    this.eveRegion=new Region;
    this.eveCity.region=this.eveRegion;
    this.evePlace.city=this.eveCity;
  }

  private updateData():void{
    //console.info(this.eventInfo.place);
    this.evePlace=this.eventInfo.place;
    this.eveCity=this.eventInfo.place.city;
    this.eveRegion=this.eventInfo.place.city.region;
    this.getEventCities(this.eveRegion);
    this.dataOdPomocnicza=this.eventInfo.startTime;
    this.dataDoPomocnicza=this.eventInfo.endTime;
    this.defaultLat = Number(this.eventInfo.place.geoWidth);
    this.defaultLng = Number(this.eventInfo.place.geoLength);
    this.markers.pop();
    this.markers.push(
      {
      lat: Number(this.eventInfo.place.geoWidth),
      lng: Number(this.eventInfo.place.geoLength),
      label: 'Twoje wydarzenie',
      draggable: true
    });
    this.updateYt();
    this.isLoading=false;



  }

  ngOnInit() {

  }



  /*
   idEvent: number;
   capacity: number; INPUT
   description: string; INPUT
   endTime: Timestamp; INPUT
   freeEntrance: boolean; INPUT
   published: boolean; INPUT
   registerEnabled: boolean; INPUT
   startTime: Timestamp; INPUT
   title: string; INPUT
   visits: number;
   youtubeLink: string; INPUT
   place: Place; INPUT INPUT INPUT INPUT MAPA
   eventType: EventType;  INPUT
   */

  //DANE GLOWNE
  idEvent:string;
  eventInfo: EventDashboardInfo;//2 w 1 INFO ORAZ FORMULARZ

  //FORMULARZ
  complexForm: FormGroup;
  isDateValid:number;
  isMapValid:number;
  isCityValid:number;
  isCapacityValid:number;

  //DANE POMOCNICZE FORMULARZA
  regions: Region[];
  eventTypes: EventType[];
  cities: City[];
  evePlace: Place;


  //METODY POMOCNICZE FORMULARZA
  getEventRegions() {
    console.info("Pobieranie regionow");
    this.http.get(this.myHttp.getUrl() + '/api/util/dictionary/regions').subscribe((data: Response)=> this.regions = data.json());
  }
  getEventTypes() {
    console.info("Pobieranie eventTypes");
    this.http.get(this.myHttp.getUrl() + '/api/util/dictionary/eventTypes').subscribe((data: Response)=> this.eventTypes = data.json());
  }

  getUpdateEventCities(region:Region){
    this.eveCity=null;
    this.getEventCities(region);
  }

  getEventCities(region: Region) {
    console.info("Pobieranie nazw miast");
    if (region!=null)
      this.http.get(this.myHttp.getUrl() + '/api/util/dictionary/cities?idRegion='+region.idRegion).subscribe((data: Response)=> this.cities = data.json());
  }

  validDates() {
    if((this.dataDoPomocnicza!=null) && (this.dataOdPomocnicza!=null)) {
      if (this.dataDoPomocnicza > this.dataOdPomocnicza) this.isDateValid = 1;
      else this.isDateValid = 0;
    } else { this.isDateValid = 0; }
  }

  validMap() {
    if (this.eventInfo.place.geoLength != null) {
      if (this.eventInfo.place.geoLength)
        this.isMapValid = 1;
      else
        this.isMapValid = 0;
    }
    else {
      this.isMapValid=0;
    }
  }

  validCity(){
    if(this.eventInfo.place.city!=null && this.eventInfo.place.city.region!=null && this.eveCity!=null && this.eveRegion!=null){
      this.isCityValid=1;
    }
    else {
      this.isCityValid=0;
    }
  }

  validCapacity(){
    if(this.eventInfo.capacity>0){
      this.isCapacityValid=1;
    }
    else {
      this.isCapacityValid=0;
    }
  }

  //METODY EDYCYJNE POL -------------------------------------------------------------
  //TYTUL
  public isTitleEdit=false;
  public editTitle(){
    (this.isTitleEdit) ? this.isTitleEdit=false : this.isTitleEdit=true;
  }

  //DATA OD
  public dataOdPomocnicza: Date;
  public dataDoPomocnicza: Date;
  public isDateEdit=false;
  public editDate(){
    (this.isDateEdit) ? this.isDateEdit=false : this.isDateEdit=true;
  }

  //REGION
  eveRegion: Region;
  public isRegionEdit=false;
  public editRegion(){
    (this.isRegionEdit) ? this.isRegionEdit=false : this.isRegionEdit=true;
  }
  //MIASTO
  eveCity: City;
  public isCityEdit=false;
  public editCity(){
    (this.isCityEdit) ? this.isCityEdit=false : this.isCityEdit=true;
  }
  //TYP WYDARZENIA
  public isTypeEdit=false;
  public editType(){
    (this.isTypeEdit) ? this.isTypeEdit=false : this.isTypeEdit=true;
  }

  //LICZBA UCZESTNIKOW
  public isCapacityEdit=false;
  public editCapacity(){
    (this.isCapacityEdit) ? this.isCapacityEdit=false : this.isCapacityEdit=true;
  }

  //WSTEP WOLNY
  public isFreeEdit=false;
  public editFree(){
    (this.isFreeEdit) ? this.isFreeEdit=false : this.isFreeEdit=true;
  }

  //OPIS
  public isDescriptionEdit=false;
  public editDescription(){
    (this.isDescriptionEdit) ? this.isDescriptionEdit=false : this.isDescriptionEdit=true;
  }

  //ULICA
  public isStreetNameEdit=false;
  public editStreetName(){
    (this.isStreetNameEdit) ? this.isStreetNameEdit=false : this.isStreetNameEdit=true;
  }

  //NR BUDYNKU
  public isStreetNoEdit=false;
  public editStreetNo(){
    (this.isStreetNoEdit) ? this.isStreetNoEdit=false : this.isStreetNoEdit=true;
  }

  //PUBLIKOWANE
  public changePublished(){
    (this.eventInfo.published) ? this.eventInfo.published=false : this.eventInfo.published=true;
  }

  //REJESTRACJA
  public changeRegister(){
    (this.eventInfo.registerEnabled) ? this.eventInfo.registerEnabled=false : this.eventInfo.registerEnabled=true;
  }

  //FILM YT
  public isYtEdit=false;
  public changeYt(){
    (this.isYtEdit) ? this.isYtEdit=false : this.isYtEdit=true;
    this.updateYt();
  }

  public updateYt(){
    if (this.eventInfo.youtubeLink.length>0){
      this.videoUrl=this.eventInfo.youtubeLink;
      this.url = this.sanitizer.bypassSecurityTrustResourceUrl(this.ytUrl+this.videoUrl); //iFrame
    }
    else {
      this.isYtEdit=true;
    }
  }

  //WYSYLANIE FORMULARZA --------------------------------------------------------------------
  postEditEvent() {
    console.log("postEditEvent()");
    //daty
    this.eventInfo.startTime = this.dateService.convDatePickerToTimestamp(this.dataOdPomocnicza);
    this.eventInfo.endTime = this.dateService.convDatePickerToTimestamp(this.dataDoPomocnicza);
    //miejsce
    this.eventInfo.place=this.evePlace;
    if(this.eveCity!=null) {
      this.eventInfo.place.city = this.eveCity;
      this.eventInfo.place.city.region = this.eveRegion;
    }

    //reszta
    console.info(this.eventInfo.idEvent);
    console.info(this.eventInfo.capacity);
    console.info(this.eventInfo.description);
    console.info(this.eventInfo.endTime);
    console.info(this.eventInfo.freeEntrance);
    console.info(this.eventInfo.published);
    console.info(this.eventInfo.registerEnabled);
    console.info(this.eventInfo.startTime);
    console.info(this.eventInfo.title);
    console.info(this.eventInfo.youtubeLink);
    console.info(this.eventInfo.place.streetName);
    console.info(this.eventInfo.place.streetNo);
    console.info(this.eventInfo.place.city.cityName);
    console.info(this.eventInfo.place.city.region.regionName);
    console.info(this.eventInfo.eventType.eventTypeName);

    this.validDates();
    this.validMap();
    this.validCity();
    this.validCapacity();
    if(this.isDateValid==1 && this.isMapValid==1 && this.isCityValid && this.isCapacityValid) {
      this.hideEdits();
      return this.http.post(this.myHttp.getUrl() + '/api/event/dashboard/edit/updateEvent', this.eventInfo, this.myHttp.postConfig())
        .subscribe((data: Response) => {
          this.postInfoAlert(data.json()), this.downloadData()
        });
    }
    else {
      alert("Formularz zawiera błędy!");
    }
  }

  private postInfoAlert(flaga:boolean){
    if(flaga){
      alert("Pomyślnie zaktualizowano wydarzenie!");
    }
    else {
      alert("Nie udało się zaktualizować wydarzenia.");
    }
  }

  private hideEdits(){
    this.isTitleEdit=false;
    this.isDateEdit=false;
    this.isRegionEdit=false;
    this.isCityEdit=false;
    this.isTypeEdit=false;
    this.isCapacityEdit=false;
    this.isFreeEdit=false;
    this.isDescriptionEdit=false;
    this.isStreetNameEdit=false;
    this.isStreetNoEdit=false;

  }
  //END WYSYLANIE FORMULARZA --------------------------------------------------------------------

  //FILM
  ytUrl="http://www.youtube.com/embed/";
  videoUrl:string;
  url: SafeResourceUrl;
  sanitizer: DomSanitizer;
  /*END: iFRAME*/

  //FLAGI
  isMapVisible=false;
  isYTVisible=false;

  public changeMapVisible(){
    (this.isMapVisible) ? this.isMapVisible=false : this.isMapVisible=true;
  }

  public changeYTVisible(){
    (this.isYTVisible) ? this.isYTVisible=false : this.isYTVisible=true;
  }


  //MAPA
  zoom: number = 12;
  defaultLat:number;
  defaultLng:number;

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
    this.eventInfo.place.geoLength=this.markers[0].lng.toString().substring(0,11);
    this.eventInfo.place.geoWidth=this.markers[0].lat.toString().substring(0,11);
    console.info("sze/dlu "+$event.coords.lat+" "+$event.coords.lng);
    console.info("sze/dlu "+this.eventInfo.place.geoWidth+" "+this.eventInfo.place.geoLength);
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
