import { Component, OnInit } from '@angular/core';
import {EventViewLecture} from "../../../_model/domainClass";
import {HttpSecService} from "../../../_service/util/http-sec.service";
import {Http,Response} from "@angular/http";
import {EventStorageService} from "../../event-storage.service";

@Component({
  selector: 'app-lectures-list',
  templateUrl: './lectures-list.component.html',
  styleUrls: ['./lectures-list.component.css']
})
export class LecturesListComponent implements OnInit {

  idEvent:string;
  lectures: EventViewLecture[]=[];

  constructor(private eventStorageService: EventStorageService, private http: Http, private myHttp: HttpSecService) { }

  ngOnInit() {
    this.idEvent = this.eventStorageService.getCurrentEventId();
    this.http.get(this.myHttp.getUrl() + '/api/event/dashboard/lectures/getList/' + this.idEvent, this.myHttp.getConfig()).subscribe((data: Response) => {
      this.lectures = data.json()
    });
  }

  updateData(){
    this.http.get(this.myHttp.getUrl() + '/api/event/dashboard/lectures/getList/' + this.idEvent, this.myHttp.getConfig()).subscribe((data: Response) => {
      this.lectures = data.json()
    });
  }

  deleteLecture(idLecture:number):void{
    this.http.get(this.myHttp.getUrl() + '/api/event/dashboard/lectures/delete/' + idLecture, this.myHttp.getConfig())
      .subscribe((data: Response) => {  this.updateData() });
  }

}
