import { Component, OnInit } from '@angular/core';
import {EventViewSpeaker} from "../../_model/domainClass";

@Component({
  selector: 'app-speaker-view',
  templateUrl: './speaker-view.component.html',
  styleUrls: ['./speaker-view.component.css']
})
export class SpeakerViewComponent  {

  public visible = false;
  private visibleAnimate = false;

  private speaker:EventViewSpeaker;

  constructor(){
    this.speaker = new EventViewSpeaker();
  }

  public show(speaker:EventViewSpeaker): void {
    this.speaker=speaker;
    this.visible = true;
    setTimeout(() => this.visibleAnimate = true, 100);
  }

  public hide(): void {
    this.visibleAnimate = false;
    this.visible = false;
    setTimeout(() => this.visible = false, 300);
  }

  public onContainerClicked(event: MouseEvent): void {
    if ((<HTMLElement>event.target).classList.contains('modal')) {
      this.hide();
    }
  }

}
