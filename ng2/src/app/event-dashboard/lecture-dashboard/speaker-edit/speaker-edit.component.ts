import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {any} from "codelyzer/util/function";
import {EventViewSpeaker} from "../../../_model/domainClass";
import {HttpSecService} from "../../../_service/util/http-sec.service";
import {Http,Response} from "@angular/http";
import {EventStorageService} from "../../event-storage.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import { FileUploader } from 'ng2-file-upload';

@Component({
  selector: 'app-speaker-edit',
  templateUrl: './speaker-edit.component.html',
  styleUrls: ['./speaker-edit.component.css']
})
export class SpeakerEditComponent implements OnInit {

  idEvent: string;
  idSpeaker: string;
  newSpeaker: EventViewSpeaker;
  speakerForm:FormGroup;
  isSpeakerValid:number=-1;

  constructor(private route: ActivatedRoute, public router: Router, public fb: FormBuilder,private eventStorageService: EventStorageService,private http: Http, private myHttp: HttpSecService) {
    this.route.params.subscribe(params=>{this.idSpeaker = params['id'];});
    this.idEvent=eventStorageService.getCurrentEventId();
    this.newSpeaker=new EventViewSpeaker;
    this.speakerForm= fb.group({
      'firstname':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(50)])),
      'lastname':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(50)])),
      'descriptionSpeaker':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(500)])),
      'email':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(50)])),
      'phone':new FormControl(null,Validators.compose([Validators.maxLength(50)]))
    });


    this.uploader = new FileUploader({url:this.myHttp.getUrl() + '/api/images/postSpeakerImage/'+this.idSpeaker,authToken:this.myHttp.getToken()});
    this.uploader.onCompleteItem = ()=>{this.updatePhoto()};
    this.uploader.onCompleteAll = ()=>{this.updatePhoto()};
    this.imageUrl= this.myHttp.getUrl()+ '/api/images/getSpeakerImage/'+this.idSpeaker+'?random+\=' + Math.random();
  }

  ngOnInit() {
    this.http.get(this.myHttp.getUrl() + '/api/event/dashboard/speakers/getSpeaker/' + this.idSpeaker, this.myHttp.getConfig()).subscribe((data: Response) => {
      this.newSpeaker = data.json()
    });
  }



  public hide(): void { //powrot
    this.router.navigate(['/event/dashboard/'+this.idEvent+'/lectures/speakers']);
  }

  public save(): void {
    this.validateSpeaker();
    if(this.isSpeakerValid){
      console.info("WALIDACJA OK")

      this.http.post(this.myHttp.getUrl()+'/api/event/dashboard/speakers/edit',this.newSpeaker,this.myHttp.postConfig())
        .subscribe((data: Response)=> {this.endRequest(data.json())});

    }
    else {
      alert("Formularz edycji zawiera błędy!");
    }
  }


  private endRequest(flag:boolean){
    if(flag){
      this.hide();
    }
    else {
      alert("Błąd! Nie udało się zaktualizować danych.")
    }

  }



  validateSpeaker(){
   if(this.speakerForm.valid){
      console.info("validateSpeaker() OK")
      this.isSpeakerValid=1;
    }
    else {
      console.info("validateSpeaker() FAIL")
      this.isSpeakerValid=0;
    }
  }

  //ZDJECIE
  public uploader:FileUploader;
  public imageUrl:string;

  public deleteImage(){
    this.http.get(this.myHttp.getUrl() + '/api/images/deleteSpeakerImage/'+this.idSpeaker,this.myHttp.getConfig()).subscribe((data: Response)=> {this.updatePhoto()});
  }

  public updatePhoto(){
    this.uploader = new FileUploader({url:this.myHttp.getUrl() + '/api/images/postSpeakerImage/'+this.idSpeaker,authToken:this.myHttp.getToken()});
    this.uploader.onCompleteItem = ()=>{this.updatePhoto()};
    this.uploader.onCompleteAll = ()=>{this.updatePhoto()};
    this.imageUrl= this.myHttp.getUrl()+ '/api/images/getSpeakerImage/'+this.idSpeaker+'?random+\=' + Math.random();
  }

}
