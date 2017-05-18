import {Component, OnInit, ViewChild} from '@angular/core';
import {UsersForAdminForm, UsersForAdminResults} from "../_model/searchDomain";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpSecService} from "../_service/util/http-sec.service";
import {CustomDateService} from "../_service/util/custom-date.service";
import {Http,Response} from "@angular/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-accounts-administration',
  templateUrl: './accounts-administration.component.html',
  styleUrls: ['./accounts-administration.component.css']
})
export class AccountsAdministrationComponent implements OnInit {

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
    this.userSearchForm = new UsersForAdminForm;
    this.userSearchForm.siteNo = 0;
  }

  getLatest() {
    this.http.post(this.myHttp.getUrl() + '/api/administration/users/search/getSearchFull', this.userSearchForm, this.myHttp.postConfig())
      .subscribe((data: Response) => {
        this.userSearchResultFull = data.json(), this.updateFullData()
      });

    this.http.post(this.myHttp.getUrl() + '/api/administration/users/search/getSearchPage', this.userSearchForm, this.myHttp.postConfig())
      .subscribe((data: Response) => {
        this.userSearchResult = data.json()
      });
  }

  /* END: INICJALIZACJA ----------------------------------------*/

  /*WYNIKI WYSZUKIWANIA*/
  public userSearchResult: UsersForAdminResults[];
  public userSearchResultFull: UsersForAdminResults[];

  /*FORMULARZ WYSZUKIWANIA*/
  public userSearchForm: UsersForAdminForm;
  public complexForm: FormGroup;

  /*WYSZUKANIE WG FORMATKI BEZ MAPY*/
  public postSearchByCriteria(): void {
    this.siteNo = 0;
    this.userSearchForm.siteNo = 0;

    this.isLoading = true;
    this.http.post(this.myHttp.getUrl() + '/api/administration/users/search/getSearchFull', this.userSearchForm, this.myHttp.postConfig())
      .subscribe((data: Response) => {
        this.userSearchResultFull = data.json(), this.updateFullData()
      });

    this.http.post(this.myHttp.getUrl() + '/api/administration/users/search/getSearchPage', this.userSearchForm, this.myHttp.postConfig())
      .subscribe((data: Response) => {
        this.userSearchResult = data.json()
      });
  }

  /*Odswiezenie danych po wyszukaniu*/
  private updateFullData(): void {
    this.siteNo = 0;
    this.userSearchForm.siteNo = 0;
    this.countEvents = this.userSearchResultFull.length;
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
    this.http.post(this.myHttp.getUrl() + '/api/administration/users/search/getSearchPage', this.userSearchForm, this.myHttp.postConfig())
      .subscribe((data: Response) => {
        this.userSearchResult = data.json(), this.updatePageData()
      });
  }

  public postPrevPage() {
    this.http.post(this.myHttp.getUrl() + '/api/administration/users/search/getSearchPage', this.userSearchForm, this.myHttp.postConfig())
      .subscribe((data: Response) => {
        this.userSearchResult = data.json(), this.updatePageData()
      });
  }

  public prevPage() {
    this.isLoading = true;
    if (this.userSearchForm.siteNo > 0) {
      this.userSearchForm.siteNo = this.userSearchForm.siteNo - 1;
      this.siteNo = this.siteNo - 1;
    }

    this.postPrevPage();
  }

  public nextPage() {
    this.isLoading = true;
    this.userSearchForm.siteNo = this.userSearchForm.siteNo + 1;
    this.siteNo = this.siteNo + 1;
    this.postNextPage();
  }

  public  updateOpinionsList():void{
    this.getLatest();
  }


}
