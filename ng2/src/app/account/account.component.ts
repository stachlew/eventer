import { Component, OnInit } from '@angular/core';
import {HttpSecService} from "../_service/util/http-sec.service";
import {Http,Response} from "@angular/http";
import {UserChangeForm} from "../_model/user.model";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  newUser: UserChangeForm;
  complexForm: FormGroup;

  isUsernameValid:boolean = false;
  isFormValid:boolean = true;

  isSuccessRegister:boolean = false;

  constructor(private http: Http, private myHttp: HttpSecService,public fb: FormBuilder, public router:Router) {
   this.newUser = new UserChangeForm;
   this.newUser.oldPass="";
   this.newUser.newPass="";
   this.newUser.phone="";
    this.complexForm = fb.group({
      'newPass':new FormControl(null,Validators.compose([ Validators.minLength(4), Validators.maxLength(50)])),
      'oldPass':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(50)])),
      'firstname':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(50)])),
      'lastname':new FormControl(null,Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(50)])),
      'phone':new FormControl(null,Validators.compose([Validators.maxLength(50)])),
    })
    this.http.get(this.myHttp.getUrl() + '/api/user/account/getInfo', this.myHttp.getConfig()).subscribe((data: Response)=> {this.newUser = data.json()});
  }
  ngOnInit() {
  }


  saveChanges(){
    console.info(this.newUser.username);
    console.info(this.newUser.email);
    console.info(this.newUser.firstname);
    console.info(this.newUser.lastname);
    console.info(this.newUser.phone);
    console.info(this.newUser.oldPass);
    console.info(this.newUser.newPass);
    return this.http.post(this.myHttp.getUrl()+'/api/user/account/postUser',this.newUser,this.myHttp.postConfig())
      .subscribe((data: Response)=> this.afterSave(data.json()));
  }

  afterSave(flag:boolean){
    if(flag){
      alert("Pomyślnie zapisano zmiany.");
      this.router.navigate(['/home']);
    }
    else {
      alert("Nie udało się zapisać zmian. Zweryfikuj poprawność hasła.");
    }
  }
}
