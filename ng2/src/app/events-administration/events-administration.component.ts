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
  selector: 'app-events-administration',
  templateUrl: './events-administration.component.html',
  styleUrls: ['./events-administration.component.css']
})
export class EventsAdministrationComponent implements OnInit {

  /*Zmienne ladowania strony*/
  public isLoading: boolean = true;

  /* INICJALIZACJA ----------------------------------------*/
  ngOnInit() {
    this.getLatest();
    this.getEventRegions();
    this.getEventTypes();
  }

  constructor(private http: Http, private myHttp: HttpSecService, public fb: FormBuilder, public dateService: CustomDateService, public router: Router) {
    this.complexForm = fb.group({
      'textContent': new FormControl(null, Validators.compose([Validators.maxLength(100)])),
      'region': new FormControl(null),
      'city': new FormControl(null),
      'dateFrom': new FormControl(null),
      'dateTo': new FormControl(null),
      'fromGeoWidth': new FormControl(null),
      'toGeoWidth': new FormControl(null),
      'fromGeoLenght': new FormControl(null),
      'toGeoLenght': new FormControl(null),
      'freeEntrance': new FormControl(null, Validators.compose([Validators.required])),
      'registerEnabled': new FormControl(null),
      'eventType': new FormControl(null)
    })
    this.eventSearchForm = new EventSearchForm;
    this.eventSearchForm.siteNo = 0;
  }

  getEventRegions() {
    console.info("Pobieranie regionow");
    this.eventSearchForm.region = null;
    this.eventSearchForm.city = null;
    this.http.get(this.myHttp.getUrl() + '/api/util/dictionary/regions').subscribe((data: Response) => {
      this.regions = data.json(), this.addBlankRegion()
    });
  }

  getEventTypes() {
    console.info("Pobieranie eventTypes");
    this.http.get(this.myHttp.getUrl() + '/api/util/dictionary/eventTypes').subscribe((data: Response) => {
      this.eventTypes = data.json(), this.addBlankEventType()
    });
  }

  getEventCities(region: Region) {
    this.eventSearchForm.city = null;
    this.cities = null;
    console.info("Pobieranie nazw miast");
    if (region != null)
      this.http.get(this.myHttp.getUrl() + '/api/util/dictionary/cities?idRegion=' + region.idRegion).subscribe((data: Response) => {
        this.cities = data.json(), this.addBlankCity()
      });
  }

  addBlankEventType() {
    this.eventTypes.splice(0, 0, null);
  }

  addBlankRegion() {
    this.regions.splice(0, 0, null);
  }

  addBlankCity() {
    this.cities.splice(0, 0, null);
  }

  getLatest() {
    this.http.post(this.myHttp.getUrl() + '/api/event/search/getSearchFull', this.eventSearchForm, this.myHttp.postConfig())
      .subscribe((data: Response) => {
        this.eventSearchResultFull = data.json(), this.updateFullData()
      });

    this.http.post(this.myHttp.getUrl() + '/api/event/search/getSearchPage', this.eventSearchForm, this.myHttp.postConfig())
      .subscribe((data: Response) => {
        this.eventSearchResult = data.json()
      });
  }

  /* END: INICJALIZACJA ----------------------------------------*/

  /*WYNIKI WYSZUKIWANIA*/
  public eventSearchResult: EventSearchResult[];
  public eventSearchResultFull: EventSearchResult[];

  /*FORMULARZ WYSZUKIWANIA*/
  public eventSearchForm: EventSearchForm;
  public complexForm: FormGroup;
  public regions: Region[];
  public cities: City[];
  public eventTypes: EventType[];
  public dataRozpoczeciaPomoc: Date;
  public dataZakonczeniaPomoc: Date;

  /*WYSZUKANIE WG FORMATKI BEZ MAPY*/
  public postSearchByCriteria(): void {
    console.info("postSearchCriteria()");
    this.eventSearchForm.dateFrom = this.dataRozpoczeciaPomoc;
    this.eventSearchForm.dateTo = this.dataZakonczeniaPomoc;
    this.eventSearchForm.toGeoWidth = null;
    this.eventSearchForm.toGeoLenght = null;
    this.eventSearchForm.fromGeoWidth = null;
    this.eventSearchForm.fromGeoLenght = null;
    this.siteNo = 0;
    this.eventSearchForm.siteNo = 0;

    this.isLoading = true;
    this.http.post(this.myHttp.getUrl() + '/api/event/search/getSearchFull', this.eventSearchForm, this.myHttp.postConfig())
      .subscribe((data: Response) => {
        this.eventSearchResultFull = data.json(), this.updateFullData()
      });

    this.http.post(this.myHttp.getUrl() + '/api/event/search/getSearchPage', this.eventSearchForm, this.myHttp.postConfig())
      .subscribe((data: Response) => {
        this.eventSearchResult = data.json()
      });
  }

  /*Odswiezenie danych po wyszukaniu*/
  private updateFullData(): void {
    this.siteNo = 0;
    this.eventSearchForm.siteNo = 0;
    this.countEvents = this.eventSearchResultFull.length;
    this.siteCount = Math.ceil(this.countEvents / 5);
    this.updatePageData();
  }

  /*Odswiezenie danych po zmianie strony*/
  private updatePageData(): void {
    if (this.siteNo > 0) {
      this.isPrevAvl = true;
    }
    else {
      this.isPrevAvl = false;
    }
    console.info("ILOSC STRON: " + this.siteCount);
    if (this.siteNo < this.siteCount - 1) {
      this.isNextAvl = true;
    }
    else {
      this.isNextAvl = false;
    }
    this.isLoading = false;
  }

  /*NAWIGACJA PO PAGINACJI*/
  public isNextAvl: boolean = false;
  public isPrevAvl: boolean = false;
  public siteNo: number = 0;
  public countEvents = 0;
  public siteCount = 0;

  public postNextPage() {
    this.http.post(this.myHttp.getUrl() + '/api/event/search/getSearchPage', this.eventSearchForm, this.myHttp.postConfig())
      .subscribe((data: Response) => {
        this.eventSearchResult = data.json(), this.updatePageData()
      });
  }

  public postPrevPage() {
    this.http.post(this.myHttp.getUrl() + '/api/event/search/getSearchPage', this.eventSearchForm, this.myHttp.postConfig())
      .subscribe((data: Response) => {
        this.eventSearchResult = data.json(), this.updatePageData()
      });
  }

  public prevPage() {
    this.isLoading = true;
    if (this.eventSearchForm.siteNo > 0) {
      this.eventSearchForm.siteNo = this.eventSearchForm.siteNo - 1;
      this.siteNo = this.siteNo - 1;
    }

    this.postPrevPage();
  }

  public nextPage() {
    this.isLoading = true;
    this.eventSearchForm.siteNo = this.eventSearchForm.siteNo + 1;
    this.siteNo = this.siteNo + 1;
    this.postNextPage();
  }
}
