import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpSecService} from "../_service/util/http-sec.service";
import {Http,Response} from "@angular/http";
import {UserRegisterForm} from "../_model/user.model";

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  newUser: UserRegisterForm;
  complexForm: FormGroup;

  isUsernameValid:boolean = false;
  isFormValid:boolean = true;

  isSuccessRegister:boolean = false;

  constructor(private http: Http, private myHttp: HttpSecService,public fb: FormBuilder) {
    this.newUser = new UserRegisterForm;
    this.complexForm = fb.group({
      'username':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(50)])),
      'firstname':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(2), Validators.maxLength(50)])),
      'lastname':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(2), Validators.maxLength(50)])),
      'email':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(50)])),
      'phone':new FormControl(null,Validators.compose([Validators.maxLength(50)])),
    })
  }

  ngOnInit() {
  }

  //sprawdzanie na biezaco
  validPageUsername() {
    console.info("Sprawdzanie dostepnosci username");
    this.http.post(this.myHttp.getUrl()+'/api/user/register/checkUsername',this.newUser,this.myHttp.postConfig())
      .subscribe((data: Response)=> this.isUsernameValid = data.json());
  }

  createNewUser(){
    console.info("createNewUser()");
    // console.info(this.newUser.username);
    // console.info(this.newUser.firstname);
    // console.info(this.newUser.lastname);
    // console.info(this.newUser.email);
    // console.info(this.newUser.phone);
    this.validEndUsernameAndRegister();
  }

  //koncowe sprawdzenie
  validEndUsernameAndRegister() {
    console.info("KONCOWA WALIDACJA");
    this.http.post(this.myHttp.getUrl()+'/api/user/register/checkUsername',this.newUser,this.myHttp.postConfig())
      .subscribe((data: Response)=> this.afterValid(data.json()));
  }

  afterValid(flag:boolean){
    console.info("KONCOWA WALIDACJA ZAKONCZONA. WYNIK USERNAME: "+flag);
    if(this.complexForm.valid && this.isUsernameValid){
      console.info("WYSLANO USERA DO ZAREJESTROWANIA");
      this.http.post(this.myHttp.getUrl()+'/api/user/register/postNewUser',this.newUser,this.myHttp.postConfig())
        .subscribe((data: Response)=> this.afterRegister(data.json()));
    }
    else {
      console.info("BLEDY W FORMULARZU. NIE WYSLANO USERA DO ZAREJESTROWANIA");
      this.isFormValid=false;
    }
  }

  afterRegister(flag:boolean):void{
    if(flag){
      //alert("Rejestracja udana.");
      this.isSuccessRegister=true;
    }
    else {
      alert("Rejestracja nieudana!");
      this.isSuccessRegister=false;
    }
  }

}
