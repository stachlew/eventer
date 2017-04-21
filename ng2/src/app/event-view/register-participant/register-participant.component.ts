import { Component, OnInit } from '@angular/core';
import {ParticipantForm} from "../../_model/domainClass";
import {HttpSecService} from "../../_service/util/http-sec.service";
import {Http,Response} from "@angular/http";

@Component({
  selector: 'app-register-participant',
  templateUrl: './register-participant.component.html',
  styleUrls: ['./register-participant.component.css']
})
export class RegisterParticipantComponent{

  public visible = false;
  private visibleAnimate = false;

  private participant:ParticipantForm;
  private firstLoad: boolean;
  private loading: boolean;
  private successRegister: boolean;


  constructor(private http: Http, private myHttp: HttpSecService){
    this.participant = new ParticipantForm;
    this.firstLoad=true;
    this.loading=false;
    this.successRegister=false;
  }

  public show(): void {
    console.info("first: "+ this.firstLoad + " loadin: "+ this.loading + " success: "+ this.successRegister );
    this.participant= new ParticipantForm;
    this.visible = true;
    this.firstLoad = true;
    this.loading = false;
    this.successRegister = false;
    setTimeout(() => this.visibleAnimate = true, 100);
  }

  public hide(): void {
    this.visibleAnimate = false;
    setTimeout(() => this.visible = false, 300);
  }

  public save(idEvent:number): void {
    this.firstLoad=false;
    this.loading=true;
    this.participant.idEvent=idEvent;
    console.info("ZAPISUJE: "+ this.participant.idEvent + " "+ this.participant.firstname + " " + this.participant.lastname + " "+ this.participant.email);

    this.firstLoad=false;
    this.http.post(this.myHttp.getUrl()+'/api/event/view/register/postNewParticipant',this.participant,this.myHttp.postConfig())
      .subscribe((data: Response)=> {this.successRegister = data.json(),this.endRequest()});
  }

  private endRequest(){
    this.loading=false;
  }



  public onContainerClicked(event: MouseEvent): void {
    if ((<HTMLElement>event.target).classList.contains('modal')) {
      this.hide();
    }
  }

}
