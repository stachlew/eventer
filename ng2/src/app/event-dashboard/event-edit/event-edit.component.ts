import { Component, OnInit } from '@angular/core';
import {EventStorageService} from "../event-storage.service";

@Component({
  selector: 'app-event-edit',
  templateUrl: './event-edit.component.html',
  styleUrls: ['./event-edit.component.css']
})
export class EventEditComponent implements OnInit {

  idEvent:string;

  constructor(private eventStorageService: EventStorageService) { }

  ngOnInit() {
    this.idEvent=this.eventStorageService.getCurrentEventId();
  }



}
