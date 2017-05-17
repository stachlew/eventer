import { Component, OnInit } from '@angular/core';
import {EventViewDetails} from "../_model/domainClass";
import {Http,Response} from "@angular/http";
import {HttpSecService} from "../_service/util/http-sec.service";

@Component({
  selector: 'app-my-events-view',
  templateUrl: './my-events-view.component.html',
  styleUrls: ['./my-events-view.component.css']
})
export class MyEventsViewComponent implements OnInit {

  constructor(private http: Http, private myHttp: HttpSecService) {
    this.http.get(this.myHttp.getUrl() + '/api/event/view/getByUser', this.myHttp.getConfig()).subscribe((data: Response)=> {this.myEvents = data.json()});
  }


  ngOnInit() {
  }

  public myEvents: EventViewDetails[] =[];

}
