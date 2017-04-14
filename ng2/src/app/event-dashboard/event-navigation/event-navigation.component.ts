import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-event-navigation',
  templateUrl: './event-navigation.component.html',
  styleUrls: ['./event-navigation.component.css']
})
export class EventNavigationComponent implements OnInit, OnDestroy {

  id: string;
  private sub: any;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params=>{
      this.id = params['id'];
    });
  }

  ngOnDestroy(){
    this.sub.unsubscribe();
  }



}
