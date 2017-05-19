import { Component } from '@angular/core';

import {HttpSecService} from "../../_service/util/http-sec.service";
import {Http,Response} from "@angular/http";
import {AccountsAdministrationComponent} from "../accounts-administration.component";
@Component({
  selector: 'app-account-mail-modal',
  templateUrl: './account-mail-modal.component.html',
  styleUrls: ['./account-mail-modal.component.css']
})
export class AccountMailModalComponent {
  public visible = false;
  private visibleAnimate = false;


  public firstLoad: boolean;
  public loading: boolean;
  public successAdd: boolean;

  private idUser: number;

  //FORMULARZ
  submitted = false;

  constructor(private http: Http, private myHttp: HttpSecService, public view: AccountsAdministrationComponent) {
    this.firstLoad = true;
    this.loading = false;
    this.successAdd = false;
  }

  public show(idUser: number): void {
    this.idUser = idUser;
    this.visible = true;
    this.firstLoad = true;
    this.loading = false;
    this.successAdd = false;
    setTimeout(() => this.visibleAnimate = true, 100);
  }


  public deleteUser(temp) {
    this.http.post(this.myHttp.getUrl()+'/api/administration/users/disableUser/'+temp,this.myHttp.getConfig()).subscribe(
      (data: Response) => {
        this.endRequest();
      });
    console.log(temp+" delete");
  }

  public hide(): void {
    this.visibleAnimate = false;
    setTimeout(() => this.visible = false, 300);
  }

  public save(): void {
    this.submitted = true;
    this.firstLoad = false;
    this.loading = true;

    this.deleteUser(this.idUser);
  }


  private endRequest() {
    this.loading = false;
    this.view.updateOpinionsList();
    this.hide();
  }


  public onContainerClicked(event: MouseEvent): void {
    if ((<HTMLElement>event.target).classList.contains('modal')) {
      this.hide();
    }
  }
}
