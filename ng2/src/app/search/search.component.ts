import {Component, OnInit, ViewChild} from '@angular/core';
import {SebmGoogleMap} from "angular2-google-maps/core";
import {EventSearchForm, EventSearchResult} from "../_model/searchDomain";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpSecService} from "../_service/util/http-sec.service";
import {CustomDateService} from "../_service/util/custom-date.service";
import {Http,Response} from "@angular/http";
import {City, EventType, Region, Timestamp} from "../_model/domainClass";
import {Router} from "@angular/router";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  /*Zmienne ladowania strony*/
  public isLoading:boolean=true;

  /* INICJALIZACJA ----------------------------------------*/
  ngOnInit() {
    this.getLatest();
    this.getEventRegions();
    this.getEventTypes();
  }

  constructor(private http: Http, private myHttp: HttpSecService, public fb: FormBuilder, public dateService: CustomDateService, public router:Router) {
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
      'registerEnabled':new FormControl(null),
      'eventType':new FormControl(null)
    })
    this.eventSearchForm=new EventSearchForm;
    this.eventSearchForm.siteNo=0;
  }

  getEventRegions() {
    console.info("Pobieranie regionow");
    this.eventSearchForm.region=null;
    this.eventSearchForm.city=null;
    this.http.get(this.myHttp.getUrl() + '/api/util/dictionary/regions').subscribe((data: Response)=> {this.regions = data.json(),this.addBlankRegion()});
  }

  getEventTypes() {
    console.info("Pobieranie eventTypes");
    this.http.get(this.myHttp.getUrl() + '/api/util/dictionary/eventTypes').subscribe((data: Response)=> {this.eventTypes = data.json(),this.addBlankEventType()});
  }

  getEventCities(region: Region) {
    this.eventSearchForm.city=null;
    this.cities=null;
    console.info("Pobieranie nazw miast");
    if (region!=null)
      this.http.get(this.myHttp.getUrl() + '/api/util/dictionary/cities?idRegion='+region.idRegion).subscribe((data: Response)=> {this.cities = data.json(),this.addBlankCity()});
  }

  addBlankEventType(){
  this.eventTypes.splice(0,0,null);
}
  addBlankRegion(){
    this.regions.splice(0,0,null);
  }
  addBlankCity(){
    this.cities.splice(0,0,null);
  }

  getLatest(){
    this.http.post(this.myHttp.getUrl()+'/api/event/search/getSearchFull',this.eventSearchForm,this.myHttp.postConfig())
      .subscribe((data: Response)=> {this.eventSearchResultFull = data.json(),this.updateFullData()});

    this.http.post(this.myHttp.getUrl()+'/api/event/search/getSearchPage',this.eventSearchForm,this.myHttp.postConfig())
      .subscribe((data: Response)=> {this.eventSearchResult = data.json()});
  }
  /* END: INICJALIZACJA ----------------------------------------*/


  /*WYNIKI WYSZUKIWANIA*/
  public eventSearchResult:EventSearchResult[];
  public eventSearchResultFull:EventSearchResult[];


  /*FORMULARZ WYSZUKIWANIA*/
  public eventSearchForm:EventSearchForm;
  public complexForm: FormGroup;
  public regions: Region[];
  public cities: City[];
  public eventTypes: EventType[];
  public dataRozpoczeciaPomoc: Date;
  public dataZakonczeniaPomoc: Date;

  /*WYSZUKANIE WG FORMATKI BEZ MAPY*/
  public postSearchByCriteria():void{
    console.info("postSearchCriteria()");
    this.eventSearchForm.dateFrom=this.dataRozpoczeciaPomoc;
    this.eventSearchForm.dateTo=this.dataZakonczeniaPomoc;
    this.eventSearchForm.toGeoWidth=null;
    this.eventSearchForm.toGeoLenght=null;
    this.eventSearchForm.fromGeoWidth=null;
    this.eventSearchForm.fromGeoLenght=null;
    this.siteNo=0;
    this.eventSearchForm.siteNo=0;

    this.isLoading=true;
    this.http.post(this.myHttp.getUrl()+'/api/event/search/getSearchFull',this.eventSearchForm,this.myHttp.postConfig())
      .subscribe((data: Response)=> {this.eventSearchResultFull = data.json(),this.updateFullData()});

    this.http.post(this.myHttp.getUrl()+'/api/event/search/getSearchPage',this.eventSearchForm,this.myHttp.postConfig())
      .subscribe((data: Response)=> {this.eventSearchResult = data.json()});
  }


  /*WYSZUKANIE WG FORMATKI Z MAPA*/
  public postSearchByGeo():void{
    console.info("postSearchGeo()");
    this.eventSearchForm.fromGeoLenght=this.fromGeoLengthNum.toString().substr(0,12);
    this.eventSearchForm.toGeoLenght=this.toGeoLengthNum.toString().substr(0,12);
    this.eventSearchForm.fromGeoWidth=this.fromGeoWidthNum.toString().substr(0,12);
    this.eventSearchForm.toGeoWidth=this.toGeoWidthNum.toString().substr(0,12);
    this.siteNo=0;
    this.eventSearchForm.siteNo=0;

    this.isLoading=true;
    this.http.post(this.myHttp.getUrl()+'/api/event/search/getSearchFull',this.eventSearchForm,this.myHttp.postConfig())
      .subscribe((data: Response)=> {this.eventSearchResultFull = data.json(),this.updateFullData()});

    this.http.post(this.myHttp.getUrl()+'/api/event/search/getSearchPage',this.eventSearchForm,this.myHttp.postConfig())
      .subscribe((data: Response)=> {this.eventSearchResult = data.json()});
  }

  /*Odswiezenie danych po wyszukaniu*/
  private updateFullData():void{
    this.refreshMarks();
    this.siteNo=0;
    this.eventSearchForm.siteNo=0;
    this.countEvents = this.eventSearchResultFull.length;
    this.siteCount = Math.ceil(this.countEvents/5);
    this.updatePageData();
  }

  /*Odswiezenie danych po zmianie strony*/
  private updatePageData():void{
    if(this.siteNo>0){
      this.isPrevAvl=true;
    }
    else {
      this.isPrevAvl=false;
    }
    console.info("ILOSC STRON: "+this.siteCount);
    if(this.siteNo<this.siteCount-1){
      this.isNextAvl=true;
    }
    else{
      this.isNextAvl=false;
    }
    this.isLoading=false;
  }

  /*NAWIGACJA PO PAGINACJI*/
  public isNextAvl:boolean = false;
  public isPrevAvl:boolean = false;
  public siteNo:number = 0;
  public countEvents = 0;
  public siteCount =0;

  public postNextPage(){
    this.http.post(this.myHttp.getUrl()+'/api/event/search/getSearchPage',this.eventSearchForm,this.myHttp.postConfig())
      .subscribe((data: Response)=> {this.eventSearchResult = data.json(),this.updatePageData()});
  }

  public postPrevPage(){
    this.http.post(this.myHttp.getUrl()+'/api/event/search/getSearchPage',this.eventSearchForm,this.myHttp.postConfig())
      .subscribe((data: Response)=> {this.eventSearchResult = data.json(),this.updatePageData()});
  }

  public prevPage(){
    this.isLoading=true;
    if(this.eventSearchForm.siteNo>0){
      this.eventSearchForm.siteNo=this.eventSearchForm.siteNo-1;
      this.siteNo=this.siteNo-1;
    }

    this.postPrevPage();
  }

  public nextPage(){
    this.isLoading=true;
    this.eventSearchForm.siteNo=this.eventSearchForm.siteNo+1;
    this.siteNo=this.siteNo+1;
    this.postNextPage();
  }





  /* MAPA OBLICZANIE  --------------------------------------------- */
  private kilometersRange:number = 126;
  private degreeRange:number = 0.0089827083*126;
  private fromGeoLengthNum:number;
  private toGeoLengthNum:number;
  private fromGeoWidthNum:number;
  private toGeoWidthNum:number;


  private kmToDeg:number=0.0139827083;

  public changeRange(value: number){
    //console.info("Zmiana wartosci na: "+value);
    this.kilometersRange=value;
    this.degreeRange=this.kilometersRange*this.kmToDeg;

    this.fromGeoLengthNum=this.markers[0].lng-this.degreeRange;
    this.toGeoLengthNum=this.markers[0].lng+this.degreeRange;
    this.fromGeoWidthNum=this.markers[0].lat-this.degreeRange;
    this.toGeoWidthNum=this.markers[0].lat+this.degreeRange;

    this.postSearchByGeo();
  }
  /*End OBLICZANIE*/


  /* MAPA */
  @ViewChild(SebmGoogleMap) map: SebmGoogleMap;

  zoom: number = 12;
  defaultLat: number = 52.25353;
  defaultLng: number = 20.90067;

  refreshMarks(){
    if(this.markers.length>1)
      this.markers.splice(1,this.markers.length);

    for(let m of this.eventSearchResultFull){
      m.geoNbLength=Number.parseFloat(m.geoLenght);
      m.geoNbWidth=Number.parseFloat(m.geoWidth);

      this.markers.push({
        lat: m.geoNbWidth,
        lng: m.geoNbLength,
        label: '+',
        draggable: false,
        description: m.description,
        idEvent: m.idEvent,
        title: m.title,
        startTime: m.startTime
      })
    }
  }

  clickedMarker(label: string, index: number) {
    //console.log(`clicked the marker: ${label || index}`)
  }

  mapClicked($event: any) {
    this.markers[0].lat=$event.coords.lat;
    this.markers[0].lng=$event.coords.lng;
    //console.info("sze/dlu CENTRALI "+$event.coords.lat+" "+$event.coords.lng);
    this.changeRange(this.kilometersRange);
    this.postSearchByGeo();

    //console.info("Zmiana wartosci na: "+this.kilometersRange);
  }

  markerDragEnd(m: marker, $event: any) {
    this.markers[0].lat=$event.coords.lat;
    this.markers[0].lng=$event.coords.lng;
    this.changeRange(this.kilometersRange);
    //this.postSearchByGeo();
    this.postSearchByGeo();
  }

  markers: marker[] = [{
    lat: this.defaultLat,
    lng: this.defaultLng,
    label: 'TU JESTEM',
    draggable: true,
    description: 'Umieść w centrum wyszukiwania',
    idEvent: -1,
    title: 'Znacznik wyszukiwania',
    startTime:null
  }];

  /* END: MAPA */


  /* Przejscie do ogloszen */
  goToEvent(id: number) {
    console.info("goToEvent"+id);
    this.router.navigate(['/event/view/'+id]);
  }
  /*END: Przejscie do ogloszen*/


  /*SLIDER Otwieranie i zamykanie slidera mapy*/
  public isSlideHide = true;

  public changeSlideStatus(){
    if(this.isSlideHide){
      (<HTMLScriptElement>document.getElementById("mySideMap")).style.width = "80%"; //pokaz

      (<HTMLScriptElement>document.getElementById("mapPannel")).style.minHeight = "400px"; //pokaz
      (<HTMLScriptElement>document.getElementById("mapPannel")).style.height = "70%"; //pokaz
      (<HTMLScriptElement>document.getElementById("mySideMapTitle")).style.height = "30%"; //pokaz

      // this.isSlideHide=false;
    }
    else{
      (<HTMLScriptElement>document.getElementById("mySideMap")).style.width = "10%";//ukryj

      (<HTMLScriptElement>document.getElementById("mapPannel")).style.minHeight = "300px"; //pokaz
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


}

/*DO MAPY*/
interface marker {
  lat: number;
  lng: number;
  label?: string;
  draggable: boolean;
  title: string;
  description: string;
  idEvent:number;
  startTime:Timestamp;
}

