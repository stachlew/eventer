import {Component, OnInit} from '@angular/core';
import {EventStorageService} from "../event-storage.service";
import {HttpSecService} from "../../_service/util/http-sec.service";
import {Http, Response} from "@angular/http";

@Component({
  selector: 'app-event-info',
  templateUrl: './event-info.component.html',
  styleUrls: ['./event-info.component.css']
})
export class EventInfoComponent implements OnInit {

  id:string;
  constructor(private eveStore: EventStorageService, private http: Http, private myHttp: HttpSecService) { }

  ngOnInit() {
    this.id=this.eveStore.getCurrentEventId();
    }



}
