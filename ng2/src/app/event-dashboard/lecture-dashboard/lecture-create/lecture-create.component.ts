/*
 EventViewLecture {
 idLecture: number;
 idEvent: number;
 lectureName: string;
 description: string;
 eventViewSpeaker: EventViewSpeaker;
 startTime: Timestamp;
 endTime: Timestamp;
 }


 EventViewSpeaker {
 idSpeaker: number;
 firstname: string;
 lastname: string;
 email: string;
 description: string;
 phone: string;
 }

 */
import { Component, OnInit } from '@angular/core';
import {EventViewLecture, EventViewSpeaker} from "../../../_model/domainClass";
import {HttpSecService} from "../../../_service/util/http-sec.service";
import {Http,Response} from "@angular/http";
import {EventStorageService} from "../../event-storage.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomDateService} from "../../../_service/util/custom-date.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-lecture-create',
  templateUrl: './lecture-create.component.html',
  styleUrls: ['./lecture-create.component.css']
})
export class LectureCreateComponent implements OnInit {

  constructor(public fb: FormBuilder, private router: Router, private eventStorageService: EventStorageService, private http: Http, private myHttp: HttpSecService, public dateService: CustomDateService) {
    this.newLecture = new EventViewLecture;
    this.newSpeaker = new EventViewSpeaker;
    this.complexForm = fb.group({
      'lectureName':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(50)])),
      'helpStartTime':new FormControl(null, Validators.compose([Validators.required])),
      'helpEndTime':new FormControl(null, Validators.compose([Validators.required])),
      'description':new FormControl(null, Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(500)])),
      //'eventViewSpeaker':new FormControl(null, Validators.compose([Validators.required])),
    });
    this.speakerForm= fb.group({
      'firstname':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(50)])),
      'lastname':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(50)])),
      'descriptionSpeaker':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(500)])),
      'email':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(50)])),
      'phone':new FormControl(null,Validators.compose([Validators.maxLength(50)]))
    })
  }

  ngOnInit() {
    //Pobierz ID
    this.idEvent = this.eventStorageService.getCurrentEventId();
    //Pobierz liste mowcow
    this.http.get(this.myHttp.getUrl() + '/api/event/dashboard/speakers/getList/' + this.idEvent, this.myHttp.getConfig())
      .subscribe((data: Response) => {this.speakers = data.json()});
  }

  //DANE
  idEvent:string;
  newLecture: EventViewLecture;
  newSpeaker: EventViewSpeaker;
  selectedSpeaker: EventViewSpeaker;
  isSpeakerCreate:boolean=false;
  speakers: EventViewSpeaker[] = [];
  complexForm: FormGroup;
  speakerForm: FormGroup;

  helpStartTime: Date;
  helpEndTime: Date;

  isDateValid:number=-1;
  isSpeakerValid:number=-1;
  isLectureValid:number=-1;

  postAddLecture(){

    console.log("postAddLecture()");
    if(this.isSpeakerCreate){
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

    if(this.isDateValid && this.isLectureValid && this.isSpeakerValid){
      console.info("WALIDACJA OK");
      console.log(this.newLecture.lectureName);
      console.log(this.newLecture.description);
      console.log(this.newLecture.startTime);
      console.log(this.newLecture.endTime);
      console.log(this.newLecture.eventViewSpeaker.idSpeaker);
      console.log(this.newLecture.eventViewSpeaker.firstname);
      console.log(this.newLecture.eventViewSpeaker.lastname);
      console.log(this.newLecture.eventViewSpeaker.email);
      console.log(this.newLecture.eventViewSpeaker.description);
      console.log(this.newLecture.eventViewSpeaker.phone);

      return this.http.post(this.myHttp.getUrl()+'/api/event/dashboard/lectures/create',this.newLecture,this.myHttp.postConfig())
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
    //wybierany
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
    if(!this.complexForm.valid){
      console.info("validLecture() BLAD")
      this.isLectureValid=0;
    }

    else if(this.complexForm.valid){
      console.info("validLecture() OK")
      this.isLectureValid=1;
    }

    else {
      console.info("validLecture() przypadek poza zakresem")
      this.isSpeakerValid=0;
    }

  }






}
