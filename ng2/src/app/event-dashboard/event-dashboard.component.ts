import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EventStorageService} from "./event-storage.service";

@Component({
  selector: 'app-event-dashboard',
  templateUrl: './event-dashboard.component.html',
  styleUrls: ['./event-dashboard.component.css'],
  providers: [EventStorageService]
})
export class EventDashboardComponent implements OnInit {

  id: string;

  constructor(private route: ActivatedRoute, private eventStorageService: EventStorageService) { }

  ngOnInit() {
    this.route.params.subscribe(params=>{this.id = params['id'];});
    this.eventStorageService.setCurrentEventId(this.id);
  }


}
