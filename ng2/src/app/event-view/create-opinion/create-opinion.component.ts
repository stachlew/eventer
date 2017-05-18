import {Component} from '@angular/core';
import {Http, Response} from "@angular/http";
import {HttpSecService} from "../../_service/util/http-sec.service";
import {EventViewOpinionForm} from "../../_model/domainClass";
import {EventViewComponent} from "../event-view.component";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-create-opinion',
  templateUrl: './create-opinion.component.html',
  styleUrls: ['./create-opinion.component.css']
})
export class CreateOpinionComponent {
  public visible = false;
  private visibleAnimate = false;

  private opinion:EventViewOpinionForm;
  private firstLoad: boolean;
  private loading: boolean;
  private successAdd: boolean;

  private idEvent: number;

  //FORMULARZ
  submitted = false;
  complexForm : FormGroup; //FORMULARZ

  constructor(private http: Http, private myHttp: HttpSecService, public view: EventViewComponent,public fb: FormBuilder){
    this.complexForm = fb.group({ //FORMULARZ
      'myContent':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(500)])),
      'email':new FormControl(null, Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(50)]))
    })

    this.opinion = new EventViewOpinionForm;
    this.firstLoad=true;
    this.loading=false;
    this.successAdd=false;
  }

  public show(idEvent:number): void {
    this.idEvent=idEvent;
    this.opinion= new EventViewOpinionForm;
    this.visible = true;
    this.firstLoad = true;
    this.loading = false;
    this.successAdd = false;
    setTimeout(() => this.visibleAnimate = true, 100);
  }

  public hide(): void {
    this.visibleAnimate = false;
    this.complexForm.reset();

    setTimeout(() => this.visible = false, 300);
  }

  public save(form: any): void {
    this.submitted = true;
    this.firstLoad=false;
    this.loading=true;

    this.opinion.email = form.email;
    this.opinion.content = form.myContent;
    this.opinion.idEvent=this.idEvent;
    this.opinion.rate=this.rateForm;
    console.info("POST OPINION: "+ this.opinion.idEvent + " "+ this.opinion.email + " " + this.opinion.content+ " " + this.opinion.rate);


    this.http.post(this.myHttp.getUrl()+'/api/event/view/opinion/postNewOpinion',this.opinion,this.myHttp.postConfig())
      .subscribe((data: Response)=> {this.successAdd = data.json(),this.endRequest()});
  }

  private endRequest(){
    this.loading=false;
    this.view.updateOpinionsList();
    this.hide();
  }


  public onContainerClicked(event: MouseEvent): void {
    if ((<HTMLElement>event.target).classList.contains('modal')) {
      this.hide();
    }
  }

  /*OCENIANIE*/
  private rateForm:number=1;
  public classStar1:string="rateStarOn";
  public classStar2:string="rateStarOff";
  public classStar3:string="rateStarOff";
  public classStar4:string="rateStarOff";
  public classStar5:string="rateStarOff";

  public rateClick(value:number){
    console.log("Rate="+value);
    this.rateForm=value;
    if(value==1){
      this.classStar1="rateStarOn";
      this.classStar2="rateStarOff";
      this.classStar3="rateStarOff";
      this.classStar4="rateStarOff";
      this.classStar5="rateStarOff";
    }
    else if(value==2){
      this.classStar1="rateStarOn";
      this.classStar2="rateStarOn";
      this.classStar3="rateStarOff";
      this.classStar4="rateStarOff";
      this.classStar5="rateStarOff";
    }
    else if(value==3){
      this.classStar1="rateStarOn";
      this.classStar2="rateStarOn";
      this.classStar3="rateStarOn";
      this.classStar4="rateStarOff";
      this.classStar5="rateStarOff";
    }
    else if(value==4){
      this.classStar1="rateStarOn";
      this.classStar2="rateStarOn";
      this.classStar3="rateStarOn";
      this.classStar4="rateStarOn";
      this.classStar5="rateStarOff";
    }
    else if(value==5){
      this.classStar1="rateStarOn";
      this.classStar2="rateStarOn";
      this.classStar3="rateStarOn";
      this.classStar4="rateStarOn";
      this.classStar5="rateStarOn";
    }

  }


}

