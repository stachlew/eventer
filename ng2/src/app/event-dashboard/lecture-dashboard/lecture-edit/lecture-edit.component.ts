import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpSecService} from "../../../_service/util/http-sec.service";
import {Http,Response} from "@angular/http";
import {EventStorageService} from "../../event-storage.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {EventViewLecture, EventViewSpeaker} from "../../../_model/domainClass";
import {CustomDateService} from "../../../_service/util/custom-date.service";

@Component({
  selector: 'app-lecture-edit',
  templateUrl: './lecture-edit.component.html',
  styleUrls: ['./lecture-edit.component.css']
})
export class LectureEditComponent implements OnInit {

  idEvent: string;
  idLecture: string;
  isSpeakerCreate:boolean=false;

  newLecture: EventViewLecture;
  newSpeaker: EventViewSpeaker;
  selectedSpeaker: EventViewSpeaker;
  speakers: EventViewSpeaker[] = [];

  complexForm:FormGroup;
  speakerForm:FormGroup;
  helpStartTime: Date;
  helpEndTime: Date;


  isDateValid:number=-1;
  isSpeakerValid:number=-1;
  isLectureValid:number=-1;

  constructor(private route: ActivatedRoute, public dateService: CustomDateService, public router: Router, public fb: FormBuilder,private eventStorageService: EventStorageService,private http: Http, private myHttp: HttpSecService) {
    this.idEvent = eventStorageService.getCurrentEventId();
    this.newLecture = new EventViewLecture;
    this.newSpeaker = new EventViewSpeaker;

    this.complexForm = this.fb.group({
      'lectureName':new FormControl(null,Validators.compose([ Validators.minLength(4), Validators.maxLength(50)])),
      'helpStartTime':new FormControl(null, Validators.compose([Validators.required])),
      'helpEndTime':new FormControl(null, Validators.compose([Validators.required])),
      'description':new FormControl(null, Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(500)])),
    });
    this.speakerForm= this.fb.group({
      'firstname':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(50)])),
      'lastname':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(50)])),
      'descriptionSpeaker':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(500)])),
      'email':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(50)])),
      'phone':new FormControl(null,Validators.compose([Validators.maxLength(50)]))
    })


  }

  ngOnInit() {
    console.info("EVENT ID: "+this.idEvent+" lecture: "+this.idLecture);

    this.route.params.subscribe(params=>{this.idLecture = params['idLecture'];});
    this.http.get(this.myHttp.getUrl() + '/api/event/dashboard/lectures/getLecture/' + this.idLecture, this.myHttp.getConfig()).subscribe((data: Response) => {
      this.newLecture = data.json(),
        this.newSpeaker=this.newLecture.eventViewSpeaker,
        this.helpStartTime = this.newLecture.startTime,
        this.helpEndTime = this.newLecture.endTime,
        this.selectedSpeaker = this.newLecture.eventViewSpeaker
      this.newLecture.lectureName=data.json().lectureName,
        this.newLecture.description=data.json().description,
        this.helpStartTime=data.json().startTime,
        this.helpEndTime=data.json().endTime
    });

    this.http.get(this.myHttp.getUrl() + '/api/event/dashboard/speakers/getList/' + this.idEvent, this.myHttp.getConfig())
      .subscribe((data: Response) => {this.speakers = data.json()});


  }

  public hide(): void { //powrot
    this.router.navigate(['/event/dashboard/'+this.idEvent+'/lectures']);
  }

  postAddLecture(){

    console.log("postAddLecture()");
    if(this.isSpeakerCreate){
      this.newLecture.eventViewSpeaker.idSpeaker=0;
      this.newLecture.eventViewSpeaker = this.newSpeaker;
    }
    else {
      this.newLecture.eventViewSpeaker = this.selectedSpeaker;
    }

    //walidacja dat

    this.newLecture.idEvent=Number(this.idEvent);
    this.newLecture.startTime = this.dateService.convDatePickerToTimestamp(this.helpStartTime);
    this.newLecture.endTime = this.dateService.convDatePickerToTimestamp(this.helpEndTime);

    this.validDates();
    this.validateSpeaker();
    this.validLecture();

    if(this.isDateValid  && this.isLectureValid && this.isSpeakerValid){
      console.info("WALIDACJA OK");
      // console.log(this.newLecture.lectureName);
      // console.log(this.newLecture.description);
      // console.log(this.newLecture.startTime);
      // console.log(this.newLecture.endTime);
      // console.log(this.newLecture.eventViewSpeaker.idSpeaker);
      // console.log(this.newLecture.eventViewSpeaker.firstname);
      // console.log(this.newLecture.eventViewSpeaker.lastname);
      // console.log(this.newLecture.eventViewSpeaker.email);
      // console.log(this.newLecture.eventViewSpeaker.description);
      // console.log(this.newLecture.eventViewSpeaker.phone);

      return this.http.post(this.myHttp.getUrl()+'/api/event/dashboard/lectures/edit',this.newLecture,this.myHttp.postConfig())
       .subscribe((data: Response)=> {this.createResponse(data.json())});
    }
    else {
      alert("Formularz zawiera błędy!");
    }
  }

  createResponse(flag:boolean){
    if(flag){
      this.router.navigate(['/event/dashboard/'+this.idEvent+'/lectures']);
    }
    else {
      alert("Błąd! Nie udało się utworzyć prelekcji.")
    }
  }

  validDates() {
    if((this.helpEndTime!=null) && (this.helpStartTime!=null)) {
      if (this.helpEndTime > this.helpStartTime) {
        console.info("validDates() OK");
        this.isDateValid = 1;
      }
      else{
        console.info("validDates() BLAD");
        this.isDateValid = 0;
      }
    }
    else {
      console.info("validDates() BLAD NULL");
      this.isDateValid = 0;
    }
  }

  validFormDates(){
    if((this.helpEndTime!=null) && (this.helpStartTime!=null)) {
      if (this.helpEndTime > this.helpStartTime) {
        console.info("validDates() OK");
        this.isDateValid = 1;
      }
      else{
        console.info("validDates() BLAD");
        this.isDateValid = 0;
      }
    }
  }


  validateSpeaker(){
    //wybierany z listy
    if(!this.isSpeakerCreate && this.selectedSpeaker==null){
      console.info("sel validateSpeaker() BLAD")
      this.isSpeakerValid=0;
    }

    else if(!this.isSpeakerCreate && this.selectedSpeaker!=null){
      console.info("sel validateSpeaker() OK")
      this.isSpeakerValid=1;
    }


    //tworzony
    else if(this.isSpeakerCreate && this.speakerForm.invalid){
      console.info("cre validateSpeaker() BLAD")
      this.isSpeakerValid=0;
    }

    else if(this.isSpeakerCreate && !this.speakerForm.invalid){
      console.info("cre validateSpeaker() OK")
      this.isSpeakerValid=1;
    }


    else {
      console.info("validateSpeaker() przypadek poza zakresem")
      this.isSpeakerValid=0;
    }

  }

  validLecture(){
    let flag:boolean=true;
    if(this.newLecture.lectureName== null || this.newLecture.lectureName.length<4 || this.newLecture.lectureName.length>50)
      flag=false;
    if(this.newLecture.description== null || this.newLecture.description.length<4 || this.newLecture.lectureName.length>500)
      flag=false;

    if(flag)
      this.isLectureValid=1;
    else
      this.isLectureValid=0;
    }


  //Tytul
  public isTitleEdit=false;
  public editTitle(){
    (this.isTitleEdit) ? this.isTitleEdit=false : this.isTitleEdit=true;
  }

  //Daty
  public isDateEdit=false;
  public editDate(){
    (this.isDateEdit) ? this.isDateEdit=false : this.isDateEdit=true;
  }

  //Opis
  public isDescriptionEdit=false;
  public editDescription(){
    (this.isDescriptionEdit) ? this.isDescriptionEdit=false : this.isDescriptionEdit=true;
  }

  //Prelegent
  public isSpeakerEdit=false;
  public editSpeaker(){
    (this.isSpeakerEdit) ? this.isSpeakerEdit=false : this.isSpeakerEdit=true;
  }
}
