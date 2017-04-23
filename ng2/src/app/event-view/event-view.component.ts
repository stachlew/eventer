import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {HttpSecService} from "../_service/util/http-sec.service";
import {Http, Response} from "@angular/http";
import {EventViewDetails, EventViewOpinion} from "../_model/domainClass";
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";
import {CreateOpinionComponent} from "./create-opinion/create-opinion.component";

@Component({
  selector: 'app-event-view',
  templateUrl: './event-view.component.html',
  styleUrls: ['./event-view.component.css'],
})
export class EventViewComponent implements OnInit, OnDestroy {

  id: string;
  private sub: any;

  /* DANE  */
  event: EventViewDetails;
  opinions: EventViewOpinion[]=[];
  /* END: DANE  */

  /* FOTO URL */
  imageUrl: string;
  speakerImageUrl: string;
  /* END: FOTO URL */

  /* FLAGI */
  isLiveYT: boolean = false;
  isLectures: boolean = false;
  isSpeakers: boolean = false;
  isOpinions: boolean = false;
  /* END: FLAGI */

  /* MAPA */
  zoom: number;
  lat: number;
  lng: number;
  markers: marker[]=[];
  /* END: MAPA */

  /*DO iFRAME*/
  ytUrl="http://www.youtube.com/embed/";
  videoUrl:string;
  url: SafeResourceUrl;
  sanitizer: DomSanitizer;
  /*END: iFRAME*/

  constructor(private route: ActivatedRoute,private http: Http, private myHttp: HttpSecService, sanitizer: DomSanitizer) {
    this.event=new EventViewDetails;
    this.sanitizer=sanitizer;
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params=>{
      this.id = params['id'];
    });
    this.http.get(this.myHttp.getUrl() + '/api/event/view/getEventDetails/'+this.id).subscribe((data: Response)=> {this.event = data.json();this.updateCordinates();this.updateSiteData();});
    this.http.get(this.myHttp.getUrl() + '/api/event/view/opinion/getOpinions/'+this.id).subscribe((data: Response)=> {this.opinions = data.json();this.updateOpinionsFlag()});

    this.imageUrl= this.myHttp.getUrl()+ '/api/images/getEventImage/'+this.id;
    this.speakerImageUrl=this.myHttp.getUrl()+ '/api/images/getSpeakerImage/';
  }

  updateCordinates(){
    this.lat=Number(this.event.geoWidth.replace(',','.'));
    this.lng=Number(this.event.geoLength.replace(',','.'));
    this.zoom=16;
    this.markers.push({
      lat: this.lat,
      lng: this.lng,
      draggable: false,
      label: '-X-',
      iconUrl: 'http://localhost:8080/marker.gif'
    });
  }

  updateOpinionsFlag(){
    if(this.opinions.length>0){
      this.isOpinions=true;
    }
  }

  public updateOpinionsList(){
    console.info("METODA RODZICA "+this.event.idEvent);
    this.http.get(this.myHttp.getUrl() + '/api/event/view/opinion/getOpinions/'+this.id).subscribe((data: Response)=> {this.opinions = data.json();this.updateOpinionsFlag()});
  }

  updateSiteData(){
    if(this.event.youtubeLink.length>0){
      this.isLiveYT=true;
      this.videoUrl=this.event.youtubeLink;
      this.url = this.sanitizer.bypassSecurityTrustResourceUrl(this.ytUrl+this.videoUrl); //iFrame
    }
    if(this.event.lectures.length>0){
      this.isLectures=true;
    }
    if(this.event.speakers.length>0){
      this.isSpeakers=true;
    }
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




}

/*DO MAPY*/
interface marker {
  lat: number;
  lng: number;
  label?: string;
  draggable: boolean;
  iconUrl: string ;
}
