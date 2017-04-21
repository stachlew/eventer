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
  speakerImageUrl: string;

  constructor(private route: ActivatedRoute,private http: Http, private myHttp: HttpSecService) {
    this.event=new EventViewDetails;
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params=>{
      this.id = params['id'];
    });
    this.http.get(this.myHttp.getUrl() + '/api/event/view/getEventDetails/'+this.id).subscribe((data: Response)=> {this.event = data.json();this.updateCordinates();});
    this.imageUrl= this.myHttp.getUrl()+ '/api/images/getEventImage/'+this.id;
    this.speakerImageUrl=this.myHttp.getUrl()+ '/api/images/getSpeakerImage/';
  }

  updateCordinates(){
    this.lat=Number(this.event.geoWidth.replace(',','.'));
    this.lng=Number(this.event.geoLength.replace(',','.'));
    this.zoom=16;

    this.markers = [];
    this.markers.push({
      lat: this.lat,
      lng: this.lng,
      draggable: false,
      label: '-X-',
      iconUrl: 'http://localhost:8080/marker.gif'
    });
  }

  ngOnDestroy(){
    this.sub.unsubscribe();
  }

  goTo(anchor: string) {
    (<HTMLScriptElement>document.querySelector('#'+ anchor)).scrollIntoView();
    window.scrollBy(0, -80);
  }

  getImageUrl(idSpeaker: number){
    return this.speakerImageUrl+idSpeaker;
  }


  /* MAPA */
  zoom: number;
  lat: number;
  lng: number;

  clickedMarker(label: string, index: number) {
    console.log(`clicked the marker: ${label || index}`)
  }

  markers: marker[];
  /* END: MAPA */

}

/*DO MAPY*/
interface marker {
  lat: number;
  lng: number;
  label?: string;
  draggable: boolean;
  iconUrl: string;
}
