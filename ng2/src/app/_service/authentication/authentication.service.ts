import { Injectable } from '@angular/core';
import { tokenNotExpired, AuthHttp } from 'angular2-jwt';
import 'rxjs/add/operator/map';
import { User } from '../../_model/user.model';
import { Cookie } from 'ng2-cookies/ng2-cookies';

@Injectable()
export class AuthenticationService {
  public token: string;
  public username: string;

  constructor(public http: AuthHttp) {
    if(this.isLoggedIn()){ //jesli odswiezana jest strona gdy uzytkownik jest zalogowany to pobierz nazwe usera z cookie
      this.username=Cookie.get('usernameCookie');
    }
  }

  /*Token jest dodatkowo zapisywany w cookie bo przy odswiezaniu strony czysci sie local storage*/
  doLogin(credentials) {
    return this.http.post('http://localhost:8080/auth', credentials)
      .map(res => {
        const data = res.json();
        if (data) {
          localStorage.setItem('token', data.token);
          localStorage.setItem('user', JSON.stringify(data.user));
          this.token=data.token;
          this.username=data.user.username;
          Cookie.set('securityToken', data.token);
          Cookie.set('usernameCookie', data.user.username);
        }
      });
  }

  doLogout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.username=null;
    Cookie.delete('securityToken');
    Cookie.delete('usernameCookie');
  }

  isLoggedIn() {
    return tokenNotExpired('token');
  }

  getRoles() {
    const user: User = JSON.parse(localStorage.getItem('user'));
    return user.authorities;
  }

}
