import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AuthenticationModule } from './_service/authentication/authentication.module';
import { AppRoutingModule, routingComponents, routingGuards } from './app.routing';
import { Ng2PageTransitionModule } from 'ng2-page-transition';

import { AppComponent } from './app.component';
import { NavigationComponent} from './navigation/navigation.component';
import { LoginFormComponent } from './login/login-form/login-form.component';
import { AlertComponent } from './alert/alert.component';

import { AuthenticationService } from './_service/authentication/authentication.service';
import { UnauthorizedComponent } from './_component/unauthorized/unauthorized.component';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AdministrationComponent } from './administration/administration.component';
import { SettingsComponent } from './settings/settings.component';
import { HttpSecService } from "./_service/util/http-sec.service";
import { FooterComponent } from './footer/footer.component';
import { FileSelectDirective, FileDropDirective } from 'ng2-file-upload';
import { AccountComponent } from './account/account.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    NavigationComponent,
    AlertComponent,
    routingComponents,
    UnauthorizedComponent,
    HomeComponent,
    LoginComponent,
    AdministrationComponent,
    SettingsComponent,
    FooterComponent,
    FileSelectDirective,
    AccountComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    AuthenticationModule,
    AppRoutingModule,
    Ng2PageTransitionModule,
  ],
  providers: [
    AuthenticationService,
    HttpSecService,
    routingGuards
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
