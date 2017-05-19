import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import {HttpSecService} from "../_service/util/http-sec.service";
import {Router} from "@angular/router";
import 'rxjs/add/operator/map';
import {Observable} from "rxjs";
import {StarsForAdminStatistics} from "../_model/searchDomain";
import 'rxjs/Rx';

@Injectable()
export class ApplicationStatisticsService {

  constructor(private http: Http, private myHttp: HttpSecService, private router: Router) { }

  getRadarChartData(): Observable<StarsForAdminStatistics[]>{
    return this.http.get(
      this.myHttp.getUrl() + '/api/administration/statistics/getStarsForLastFinished')
      .map(data => data.json());
  }

  getLineChartData(): Observable<number[]>{
    return this.http.get(
      this.myHttp.getUrl() + '/api/administration/statistics/getSumVisitsForMonth')
      .map(data => data.json());
  }

}
