import { Component, OnInit } from '@angular/core';
import {EventStorageService} from "../event-storage.service";

@Component({
  selector: 'app-statistics-view',
  templateUrl: './statistics-view.component.html',
  styleUrls: ['./statistics-view.component.css']
})
export class StatisticsViewComponent implements OnInit {

  id:string;

  constructor(private eventStorageService: EventStorageService) { }

  ngOnInit() {
    this.id=this.eventStorageService.getCurrentEventId();
  }
}
