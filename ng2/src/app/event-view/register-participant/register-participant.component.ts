import {Component } from '@angular/core';
import {ParticipantForm} from "../../_model/domainClass";
import {HttpSecService} from "../../_service/util/http-sec.service";
import {Http,Response} from "@angular/http";

import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-register-participant',
  templateUrl: './register-participant.component.html',
  styleUrls: ['./register-participant.component.css']
})
export class RegisterParticipantComponent{

  public visible = false;
  private visibleAnimate = false;

  private idEvent:number;
  private participant:ParticipantForm;
  private firstLoad: boolean;
  private loading: boolean;
  private successRegister: boolean;

  //FORMULARZ
  submitted = false;
  complexForm : FormGroup; //FORMULARZ


  constructor(private http: Http, private myHttp: HttpSecService, public fb: FormBuilder){
    this.complexForm = fb.group({ //FORMULARZ
      'firstname':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(50)])),
      'lastname':new FormControl(null, Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(50)])),
      'email':new FormControl(null, Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(50)]))
    })


    this.participant = new ParticipantForm;
    this.firstLoad=true;
    this.loading=false;
    this.successRegister=false;
  }

  public show(idEvent:number): void {
    this.idEvent=idEvent;
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

  public save(form: any): void {
    this.submitted = true;
    this.firstLoad=false;
    this.loading=true;

    this.participant.firstname=form.firstname;
    this.participant.lastname=form.lastname;
    this.participant.email=form.email;
    this.participant.idEvent=this.idEvent;
    console.info("ID: "+this.participant.idEvent + "F: "+this.participant.firstname +" L: "+this.participant.lastname + " E "+this.participant.email);



    this.http.post(this.myHttp.getUrl()+'/api/event/view/register/postNewParticipant',this.participant,this.myHttp.postConfig())
      .subscribe((data: Response)=> {this.successRegister = data.json(),this.endRequest()});
  }

  private endRequest(){
    this.loading=false;
    this.participant=new ParticipantForm();
    this.participant.idEvent=this.idEvent;
  }



  public onContainerClicked(event: MouseEvent): void {
    if ((<HTMLElement>event.target).classList.contains('modal')) {
      this.hide();
    }
  }

}
