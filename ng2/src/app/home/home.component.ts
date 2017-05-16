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
  imageUrl: string = this.myHttp.getUrl()+ "/api/images/getEventImage/";
  possibilityState: string = 'ON';
  statisticState: string = 'OFF';

  constructor(private http: Http, private myHttp: HttpSecService, private router: Router) {

  }

  ngOnInit() {
    this.getLatest();
    setInterval(() => this.changeState(), 5000);
  }

  changeState() {
    this.possibilityState = (this.possibilityState === 'ON' ? 'OFF' : 'ON');
    this.statisticState = (this.statisticState === 'ON' ? 'OFF' : 'ON');
  }

  getLatest(){
    this.http.get(this.myHttp.getUrl() + '/api/event/view/getLatest').subscribe((data: Response)=> this.latestEvents = data.json());
  }

  goToEvent(id: number) {
    this.router.navigate(['/event/view/'+id]);
  }



}
