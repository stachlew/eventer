import { Component, OnInit } from '@angular/core';
import {EventStorageService} from "../event-storage.service";
import {EventViewPartcipant} from "../../_model/dashboardClass";
import {Http, Response} from "@angular/http";
import {HttpSecService} from "../../_service/util/http-sec.service";

@Component({
  selector: 'app-event-guests',
  templateUrl: './event-guests.component.html',
  styleUrls: ['./event-guests.component.css']
})
export class EventGuestsComponent implements OnInit {

  idEvent:string;
  guests: EventViewPartcipant[] = [];

  constructor(private eventStorageService: EventStorageService, private http: Http, private myHttp: HttpSecService) {

  }

  ngOnInit() {
    this.idEvent = this.eventStorageService.getCurrentEventId();
    this.http.get(this.myHttp.getUrl() + '/api/event/dashboard/guests/getList/' + this.idEvent, this.myHttp.getConfig()).subscribe((data: Response) => {
      this.guests = data.json()
    });
  }

  public changePresence(guest: EventViewPartcipant):void{
    if(guest!=null){
      (guest.presence) ? guest.presence=false : guest.presence=true;
      this.http.post(this.myHttp.getUrl() + '/api/event/dashboard/guests/setPresence', guest, this.myHttp.postConfig())
        .subscribe((data: Response) => { });
    }
  }

}
