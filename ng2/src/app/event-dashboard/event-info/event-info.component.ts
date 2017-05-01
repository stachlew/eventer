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


  constructor(private eveStore: EventStorageService, private http: Http, private myHttp: HttpSecService) {
    this.eventInfo = new EventDashboardInfo;
    this.stats = new EventDashboardStatisticsInfo;
  }

  ngOnInit() {
    this.idEvent=this.eveStore.getCurrentEventId();
    this.http.get(this.myHttp.getUrl() + '/api/event/dashboard/edit/getEventInfo/'+this.idEvent,this.myHttp.getConfig()).subscribe((data: Response)=> {this.eventInfo = data.json()});
    this.http.get(this.myHttp.getUrl() + '/api/event/dashboard/statistics/getEventStats/'+this.idEvent,this.myHttp.getConfig()).subscribe((data: Response)=> {this.stats = data.json()});
    }



}
