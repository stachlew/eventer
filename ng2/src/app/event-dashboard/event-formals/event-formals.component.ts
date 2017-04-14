import { Component, OnInit } from '@angular/core';
import {EventStorageService} from "../event-storage.service";

@Component({
  selector: 'app-event-formals',
  templateUrl: './event-formals.component.html',
  styleUrls: ['./event-formals.component.css']
})
export class EventFormalsComponent implements OnInit {

  id:string;

  constructor(private eventStorageService: EventStorageService) { }

  ngOnInit() {
    this.id=this.eventStorageService.getCurrentEventId();
  }
}
