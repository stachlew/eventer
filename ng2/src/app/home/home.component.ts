import { Component, OnInit } from '@angular/core';
import { EventHeader} from "../_model/domainClass";
import {Http,Response} from "@angular/http";
import {HttpSecService} from "../_service/util/http-sec.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  latestEvents : EventHeader[];

  constructor(private http: Http, private myHttp: HttpSecService) {

  }

  ngOnInit() {
    this.getLatest();
  }

  getLatest(){
    this.http.get(this.myHttp.getUrl() + '/api/events/getLatest').subscribe((data: Response)=> this.latestEvents = data.json());
  }



}
