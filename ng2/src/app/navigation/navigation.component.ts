import { Component, trigger, state, transition, style, animate } from '@angular/core';
import { AuthenticationService } from '../_service/authentication/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'navigation-bar',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent {

  ngOnInit() {
  }

  isIn = false;   // store state
  toggleState() { // click handler dla wersji mobile/desktop
    let bool = this.isIn;
    this.isIn = bool === false ? true : false;
  }

  constructor( public auth: AuthenticationService, public router: Router) {
  }

  logout() {
    this.auth.doLogout();
    this.router.navigate(['login']);
  }

  isAuth() {
    if(this.auth.isLoggedIn()) {
      return true;
    }
  }

}
