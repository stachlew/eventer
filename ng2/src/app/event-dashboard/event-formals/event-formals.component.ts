import { Component, OnInit } from '@angular/core';
import {EventStorageService} from "../event-storage.service";
import {HttpSecService} from "../../_service/util/http-sec.service";
import {Http, Response} from "@angular/http";
import {document} from "@angular/platform-browser/src/facade/browser";

@Component({
  selector: 'app-event-formals',
  templateUrl: './event-formals.component.html',
  styleUrls: ['./event-formals.component.css']
})
export class EventFormalsComponent implements OnInit {
// /api/documents/download/{idEvent}/{docNo}
  id:string;

  documents: string[] = [];

  constructor(private eventStorageService: EventStorageService, private http: Http, private myHttp: HttpSecService) {
    this.id = this.eventStorageService.getCurrentEventId();
    this.http.get(this.myHttp.getUrl() + '/api/documents/info',this.myHttp.getConfig()).subscribe((data: Response)=> {this.documents = data.json()});
  }


  ngOnInit() {
  }

  downloadFile(docNo:number) {
    this.myHttp.getDOCXFromApi('/api/documents/download/'+this.id+'/'+docNo,'Wniosek '+this.documents[docNo]);
  }

}
