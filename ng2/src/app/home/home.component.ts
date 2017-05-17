import {Component, OnInit, trigger, state, transition, animate, style} from '@angular/core';
import { EventHeader} from "../_model/domainClass";
import {Http,Response} from "@angular/http";
import {HttpSecService} from "../_service/util/http-sec.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  animations: [
    trigger('possibility', [
      state('ON' , style({ opacity: 1, transform: 'scale(1.0)' })),
      state('OFF', style({ opacity: 0, transform: 'scale(0.0)'  })),

      transition('ON => OFF', animate('300ms')),
      transition('OFF => ON', animate('300ms'))
    ]),
    trigger('statistic', [
      state('ON' , style({ opacity: 1, transform: 'scale(1.0)' })),
      state('OFF', style({ opacity: 0, transform: 'scale(0.0)'  })),

      transition('ON => OFF', animate('300ms')),
      transition('OFF => ON', animate('300ms'))
    ])
  ]
})
export class HomeComponent implements OnInit {

  latestEvents : EventHeader[];
  lastMinuteEvents : EventHeader[];
  theMostCommonEvents : EventHeader[];
  imageUrl: string = this.myHttp.getUrl()+ "/api/images/getEventImage/";
  possibilityState: string = 'ON';
  statisticState: string = 'OFF';
  createEvents: string = "";
  allVisitors: string = "";
  avgVisitors: string = "";
  activeUsers: string = "";
  allGuests: string = "";

  constructor(private http: Http, private myHttp: HttpSecService, private router: Router) {

  }

  ngOnInit() {
    this.getLatest();
    this.getLastMinuteEvents();
    this.getTheMostCommonEvents();
    this.getStatistic();
    setInterval(() => this.changeState(), 5000);
  }

  changeState() {
    this.possibilityState = (this.possibilityState === 'ON' ? 'OFF' : 'ON');
    this.statisticState = (this.statisticState === 'ON' ? 'OFF' : 'ON');
  }

  getLatest(){
    this.http.get(this.myHttp.getUrl() + '/api/event/view/getLatest').subscribe((data: Response)=> this.latestEvents = data.json());
  }

  getLastMinuteEvents() {
    this.http.get(this.myHttp.getUrl() + '/api/event/search/getLastMinute').subscribe((data: Response)=> this.lastMinuteEvents = data.json());
  }

  getTheMostCommonEvents() {
    this.http.get(this.myHttp.getUrl() + '/api/event/search/getMostVisited').subscribe((data: Response)=> this.theMostCommonEvents = data.json());
  }

  getStatistic() {
    this.http.get(this.myHttp.getUrl() + '/api/administration/statistics/getCountOfEvents').subscribe((data: Response)=> this.createEvents = data.text());
    this.http.get(this.myHttp.getUrl() + '/api/administration/statistics/getCountOfAllVisits').subscribe((data: Response)=> this.allVisitors = data.text());
    this.http.get(this.myHttp.getUrl() + '/api/administration/statistics/getAverageOfAllVisits').subscribe((data: Response)=> this.avgVisitors = data.text());
    this.http.get(this.myHttp.getUrl() + '/api/administration/statistics/getCountOfAllActiveUsers').subscribe((data: Response)=> this.activeUsers = data.text());
    this.http.get(this.myHttp.getUrl() + '/api/administration/statistics/getCountParticipant').subscribe((data: Response)=> this.allGuests = data.text());
  }

  goToEvent(id: number) {
    this.router.navigate(['/event/view/'+id]);
  }
}
