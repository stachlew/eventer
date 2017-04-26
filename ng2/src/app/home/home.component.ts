import { Component, OnInit } from '@angular/core';
import { EventHeader} from "../_model/domainClass";
import {Http,Response} from "@angular/http";
import {HttpSecService} from "../_service/util/http-sec.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  latestEvents : EventHeader[];
  imageUrl: string = this.myHttp.getUrl()+ "/api/images/getEventImage/";

  constructor(private http: Http, private myHttp: HttpSecService, private router: Router) {

  }

  ngOnInit() {
    this.getLatest();
  }

  getLatest(){
    this.http.get(this.myHttp.getUrl() + '/api/event/view/getLatest').subscribe((data: Response)=> this.latestEvents = data.json());
  }

  goToEvent(id: number) {
    this.router.navigate(['/event/view/'+id]);
  }



}
