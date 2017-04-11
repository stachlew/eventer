import { Injectable } from '@angular/core';
import {Http, RequestOptions, Headers, ResponseContentType, URLSearchParams} from "@angular/http";
import { Cookie } from 'ng2-cookies/ng2-cookies';
import 'rxjs/Rx' ;
import {Observable} from "rxjs";
import {map} from "rxjs/operator/map";

@Injectable()
export class HttpSecService {

  applicationUrl = "http://localhost:8080/";
  private logAndPassOn;

  //POBIERANIE PLIKOW: ------------------------------------
  //1. Metoda do wywolania z aplikacji [WYWOLYWAC JA]
  public getDOCXFromApi(url: string, fileName: string) {
    this.getFileFromBackend(url)
      .subscribe(data => this.getDownloadWindow(data,fileName)),//console.log(data),
      error => console.log("Error downloading the file."),
      () => console.log('Completed file download.');
  }

  //2. Zapytanie wysylane do api
  private getFileFromBackend(url: string): Observable<Object[]> {
    return Observable.create(observer => {
      let xhr = new XMLHttpRequest();
      let securityToken = Cookie.get('securityToken');


      xhr.open('GET', this.applicationUrl+url, true);
      xhr.setRequestHeader('Content-type', 'application/json');
      xhr.setRequestHeader('Authorization', securityToken);
      xhr.responseType='blob';
      xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
          if (xhr.status === 200) {
            var contentType = 'application/vnd.openxmlformats-officedocument.wordprocessingml.document';
            var blob = new Blob([xhr.response], { type: contentType });
            observer.next(blob);
            observer.complete();
          }
          else {
            observer.error(xhr.response);
          }
        }
      }
      xhr.send();
    });
  }

  //3. Metoda wywolujaca okno pobierania i ustawienia dla pobierania
  private getDownloadWindow(data,fileName: string){
    var link=document.createElement('a');
    link.href=window.URL.createObjectURL(data);
    link.download=fileName;
    link.click();
  }
  //END: POBIERANIE PLIKOW ------------------------------------


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
