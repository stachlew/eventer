import {Component, OnInit} from '@angular/core';
import {EventStorageService} from "../event-storage.service";
import {HttpSecService} from "../../_service/util/http-sec.service";
import {Http, Response} from "@angular/http";
import {EventDashboardInfo, EventDashboardStatisticsInfo} from "../../_model/dashboardClass";

@Component({
  selector: 'app-event-info',
  templateUrl: './event-info.component.html',
  styleUrls: ['./event-info.component.css']
})
export class EventInfoComponent implements OnInit {

  idEvent:string;
  isLoading:boolean;
  eventInfo: EventDashboardInfo;
  stats: EventDashboardStatisticsInfo;
  public average: number =0;

  constructor(private eveStore: EventStorageService, private http: Http, private myHttp: HttpSecService) {
    this.eventInfo = new EventDashboardInfo;
    this.stats = new EventDashboardStatisticsInfo;
  }

  ngOnInit() {
    this.idEvent=this.eveStore.getCurrentEventId();
    this.http.get(this.myHttp.getUrl() + '/api/event/dashboard/edit/getEventInfo/'+this.idEvent,this.myHttp.getConfig()).subscribe((data: Response)=> {this.eventInfo = data.json()});
    this.http.get(this.myHttp.getUrl() + '/api/event/dashboard/statistics/getEventStats/'+this.idEvent,this.myHttp.getConfig()).subscribe((data: Response)=> {this.stats = data.json(),this.countAverage(data.json())});
    }

  countAverage(stats: EventDashboardStatisticsInfo){
    let sum1:number = stats.stars1 * 1;
    let sum2:number = stats.stars2 * 2;
    let sum3:number = stats.stars3 * 3;
    let sum4:number = stats.stars4 * 4;
    let sum5:number = stats.stars5 * 5;
    let sumAll = sum1+sum2+sum3+sum4+sum5;
    let sumStars = stats.stars1+stats.stars2+stats.stars3+stats.stars4+stats.stars5;
    if(sumStars>0)
      this.average = sumAll/sumStars;
    else
      this.average=0;
  }


}
