import { Component, OnInit } from '@angular/core';
import {EventStorageService} from "../event-storage.service";

@Component({
  selector: 'app-event-guests',
  templateUrl: './event-guests.component.html',
  styleUrls: ['./event-guests.component.css']
})
export class EventGuestsComponent implements OnInit {

  id:string;

  constructor(private eventStorageService: EventStorageService) { }

  ngOnInit() {
    this.id=this.eventStorageService.getCurrentEventId();
  }

}
