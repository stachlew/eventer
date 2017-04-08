import { Injectable } from '@angular/core';
import {Http, RequestOptions, Headers} from "@angular/http";
import { Cookie } from 'ng2-cookies/ng2-cookies';


@Injectable()
export class HttpSecService {

  applicationUrl = "http://localhost:8080/";

  /*Zwraca opcje dla requesta ktory wymaga autoryzacji*/
  public getConfig(){
    let securityToken = Cookie.get('securityToken');
    let headers = new Headers({ 'Authorization': securityToken });
    let options = new RequestOptions({ headers: headers });
    return options;
  }

  public postConfig(){
    let securityToken = Cookie.get('securityToken');
    let headers = new Headers({ 'Authorization': securityToken , 'Content-Type': 'application/json'});
    let options = new RequestOptions({ headers: headers });
    return options;
  }

  public getToken(){
    let securityToken = Cookie.get('securityToken');
    return securityToken;
  }

  public getUrl(){
    return this.applicationUrl;
  }

  constructor(private http: Http) { }

}
