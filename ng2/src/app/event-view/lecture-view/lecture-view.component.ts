import { Component } from '@angular/core';
import {EventViewLecture, EventViewSpeaker} from "../../_model/domainClass";

@Component({
  selector: 'app-lecture-view',
  templateUrl: './lecture-view.component.html',
  styleUrls: ['./lecture-view.component.css']
})
export class LectureViewComponent {

  public visible = false;
  private visibleAnimate = false;

  private lecture:EventViewLecture;
  private speaker: EventViewSpeaker;

  constructor(){
    this.lecture = new EventViewLecture();
    this.speaker = new EventViewSpeaker();
  }

  public show(lecture:EventViewLecture, speaker:EventViewSpeaker): void {
    this.lecture=lecture;
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
