import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {HttpSecService} from "../_service/util/http-sec.service";
import {Http, Response} from "@angular/http";
import {EventViewDetails} from "../_model/domainClass";

@Component({
  selector: 'app-event-view',
  templateUrl: './event-view.component.html',
  styleUrls: ['./event-view.component.css']
})
export class EventViewComponent implements OnInit, OnDestroy {

  id: string;
  private sub: any;

  event: EventViewDetails;
  imageUrl: string;

  constructor(private route: ActivatedRoute,private http: Http, private myHttp: HttpSecService) {
    this.event=new EventViewDetails;
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params=>{
      this.id = params['id'];
    });
    this.http.get(this.myHttp.getUrl() + '/api/event/view/getEventDetails/'+this.id).subscribe((data: Response)=> this.event = data.json());
    this.imageUrl= this.myHttp.getUrl()+ '/api/images/getImage/'+this.id;
  }

  ngOnDestroy(){
    this.sub.unsubscribe();
  }

  goTo(anchor: string) {
    (<HTMLScriptElement>document.querySelector('#'+ anchor)).scrollIntoView();
    window.scrollBy(0, -80);
  }
}
