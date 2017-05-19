import {Component} from '@angular/core';
import {Http, Response} from "@angular/http";
import {HttpSecService} from "../../_service/util/http-sec.service";
import {EventViewOpinionForm} from "../../_model/domainClass";

import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {EventsAdministrationComponent} from "../events-administration.component";

@Component({
  selector: 'app-event-mail-modal',
  templateUrl: './event-mail-modal.component.html',
  styleUrls: ['./event-mail-modal.component.css']
})
export class EventMailModalComponent {
  public visible = false;
  private visibleAnimate = false;

  private opinion:EventViewOpinionForm;
  private firstLoad: boolean;
  private loading: boolean;
  private successAdd: boolean;

  private idEvent: number;

  //FORMULARZ
  submitted = false;
  complexForm : FormGroup; //FORMULARZ

  constructor(private http: Http, private myHttp: HttpSecService, public view: EventsAdministrationComponent,public fb: FormBuilder){
    this.complexForm = fb.group({ //FORMULARZ
      'myContent':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(500)])),
    })

    this.opinion = new EventViewOpinionForm;
    this.firstLoad=true;
    this.loading=false;
    this.successAdd=false;
  }

  public show(idEvent:number): void {
    this.idEvent=idEvent;
    this.opinion= new EventViewOpinionForm;
    this.visible = true;
    this.firstLoad = true;
    this.loading = false;
    this.successAdd = false;
    setTimeout(() => this.visibleAnimate = true, 100);
  }

  public hide(): void {
    this.visibleAnimate = false;
    this.complexForm.reset();

    setTimeout(() => this.visible = false, 300);
  }

  public save(form: any): void {
    this.submitted = true;
    this.firstLoad=false;
    this.loading=true;

    this.unpublishEvent(this.idEvent,form.myContent);
  }

  public unpublishEvent(id:number,message:string) {
    this.http.post(this.myHttp.getUrl()+'/api/administration/events/changePublished/'+id,message,this.myHttp.getConfig()).subscribe(
      (data: Response) => {        this.endRequest();      });
  }

  private endRequest(){
    this.loading=false;
    this.view.updateOpinionsList();
    this.hide();
  }



  public onContainerClicked(event: MouseEvent): void {
    if ((<HTMLElement>event.target).classList.contains('modal')) {
      this.hide();
    }
  }
}
