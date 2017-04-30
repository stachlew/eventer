import {Injectable, OnInit} from '@angular/core';
import {EventDashboardInfo, EventDashboardStatisticsInfo} from "../_model/dashboardClass";
import {HttpSecService} from "../_service/util/http-sec.service";
import {Http, Response} from "@angular/http";

@Injectable()
export class EventStorageService implements OnInit{

  private currentEventId: string;

  constructor() {
  }

  ngOnInit(): void {
  }


  getCurrentEventId(): string {
    return this.currentEventId;
  }

  setCurrentEventId(value: string) {
    this.currentEventId = value;
  }
}
