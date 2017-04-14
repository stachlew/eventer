import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EventStorageService} from "../event-storage.service";

@Component({
  selector: 'app-event-info',
  templateUrl: './event-info.component.html',
  styleUrls: ['./event-info.component.css']
})
export class EventInfoComponent implements OnInit {

  id:string;

  constructor(private eventStorageService: EventStorageService) { }

  ngOnInit() {
    this.id=this.eventStorageService.getCurrentEventId();
  }



}
