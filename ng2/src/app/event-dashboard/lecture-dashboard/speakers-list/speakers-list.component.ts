import { Component, OnInit } from '@angular/core';
import {EventViewSpeaker} from "../../../_model/domainClass";
import {HttpSecService} from "../../../_service/util/http-sec.service";
import {Http, Response} from "@angular/http";
import {EventStorageService} from "../../event-storage.service";

@Component({
  selector: 'app-speakers-list',
  templateUrl: './speakers-list.component.html',
  styleUrls: ['./speakers-list.component.css']
})
export class SpeakersListComponent implements OnInit {

  idEvent:string;
  speakers: EventViewSpeaker[]=[];

  constructor(private eventStorageService: EventStorageService, private http: Http, private myHttp: HttpSecService) { }

  ngOnInit() {
    this.idEvent = this.eventStorageService.getCurrentEventId();
    this.http.get(this.myHttp.getUrl() + '/api/event/dashboard/speakers/getList/' + this.idEvent, this.myHttp.getConfig()).subscribe((data: Response) => {
      this.speakers = data.json()
    });
  }

}
