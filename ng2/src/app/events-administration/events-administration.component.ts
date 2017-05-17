import {Component, OnInit, ViewChild} from '@angular/core';
import {EventToSearchForAdminForm, EventForAdminResult} from "../_model/searchDomain";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpSecService} from "../_service/util/http-sec.service";
import {CustomDateService} from "../_service/util/custom-date.service";
import {Http,Response} from "@angular/http";
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
  }

  constructor(private http: Http, private myHttp: HttpSecService, public fb: FormBuilder, public dateService: CustomDateService, public router: Router) {
    this.complexForm = fb.group({
      'textContent': new FormControl(null, Validators.compose([Validators.maxLength(100)]))
    })
    this.eventSearchForm = new EventToSearchForAdminForm;
    this.eventSearchForm.siteNo = 0;
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
  public eventSearchResult: EventForAdminResult[];
  public eventSearchResultFull: EventForAdminResult[];

  /*FORMULARZ WYSZUKIWANIA*/
  public eventSearchForm: EventToSearchForAdminForm;
  public complexForm: FormGroup;

  /*WYSZUKANIE WG FORMATKI BEZ MAPY*/
  public postSearchByCriteria(): void {
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
    // console.info("ILOSC STRON: " + this.siteCount);
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
