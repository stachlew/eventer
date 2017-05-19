import { Component, OnInit } from '@angular/core';
import {EventsAdministrationComponent} from "../events-administration.component";
import {HttpSecService} from "../../_service/util/http-sec.service";
import {Http,Response} from "@angular/http";

@Component({
  selector: 'app-event-warn-modal',
  templateUrl: './event-warn-modal.component.html',
  styleUrls: ['./event-warn-modal.component.css']
})
export class EventWarnModalComponent {
  public visible = false;
  private visibleAnimate = false;


  public firstLoad: boolean;
  public loading: boolean;
  public successAdd: boolean;

  private idEvent: number;

  //FORMULARZ
  submitted = false;

  constructor(private http: Http, private myHttp: HttpSecService, public view: EventsAdministrationComponent) {
    this.firstLoad = true;
    this.loading = false;
    this.successAdd = false;
  }

  public show(idEvent: number): void {
    this.idEvent = idEvent;
    this.visible = true;
    this.firstLoad = true;
    this.loading = false;
    this.successAdd = false;
    setTimeout(() => this.visibleAnimate = true, 100);
  }

  public hide(): void {
    this.visibleAnimate = false;
    setTimeout(() => this.visible = false, 300);
  }

  public save(): void {
    this.submitted = true;
    this.firstLoad = false;
    this.loading = true;

    this.deleteEvent(this.idEvent);
  }

  public deleteEvent(temp) {
    this.http.post(this.myHttp.getUrl()+'/api/administration/events/delete/'+temp,this.myHttp.getConfig()).subscribe(
      (data: Response) => {
        this.endRequest();
      });
    console.log(temp+" delete");
  }

  private endRequest() {
    this.loading = false;
    this.view.updateOpinionsList();
    this.hide();
  }


  public onContainerClicked(event: MouseEvent): void {
    if ((<HTMLElement>event.target).classList.contains('modal')) {
      this.hide();
    }
  }
}
