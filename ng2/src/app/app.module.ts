import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AuthenticationModule } from './_service/authentication/authentication.module';
import { AppRoutingModule, routingComponents, routingGuards } from './app.routing';
import { Ng2PageTransitionModule } from 'ng2-page-transition';
// import { DateTimePickerDirective } from 'ng2-eonasdan-datetimepicker';

import { AppComponent } from './app.component';
import { NavigationComponent} from './navigation/navigation.component';
import { LoginFormComponent } from './login/login-form/login-form.component';
import { AlertComponent } from './alert/alert.component';

import { AuthenticationService } from './_service/authentication/authentication.service';
import { UnauthorizedComponent } from './_component/unauthorized/unauthorized.component';

import { DemoComponent } from './demo/demo.component';
import { LoginComponent } from './login/login.component';
import { HttpSecService } from "./_service/util/http-sec.service";
import { CustomDateService } from "./_service/util/custom-date.service";
import { FooterComponent } from './footer/footer.component';
import { FileSelectDirective } from 'ng2-file-upload';
import { AccountComponent } from './account/account.component';
import { AgmCoreModule } from "angular2-google-maps/core";
import { HomeComponent } from './home/home.component';
import { SearchComponent } from './search/search.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { EventViewComponent } from './event-view/event-view.component';
import { RegisterParticipantComponent } from './event-view/register-participant/register-participant.component';
import { EventCreateComponent } from './event-create/event-create.component';
import { EventDashboardComponent } from './event-dashboard/event-dashboard.component';
import { EventEditComponent } from './event-dashboard/event-edit/event-edit.component';
import { StatisticsViewComponent } from './event-dashboard/statistics-view/statistics-view.component';
import { EventFormalsComponent } from './event-dashboard/event-formals/event-formals.component';
import { EventGuestsComponent } from './event-dashboard/event-guests/event-guests.component';
import { MyEventsViewComponent } from './my-events-view/my-events-view.component';
import { AccountsAdministrationComponent } from './accounts-administration/accounts-administration.component';
import { EventsAdministrationComponent } from './events-administration/events-administration.component';
import { ApplicationStatisticsComponent } from './application-statistics/application-statistics.component';
import { EventNavigationComponent } from './event-dashboard/event-navigation/event-navigation.component';
import { EventInfoComponent } from './event-dashboard/event-info/event-info.component';
import { LectureDashboardComponent } from './event-dashboard/lecture-dashboard/lecture-dashboard.component';
import { LectureNavigationComponent } from './event-dashboard/lecture-dashboard/lecture-navigation/lecture-navigation.component';
import { LectureCreateComponent } from './event-dashboard/lecture-dashboard/lecture-create/lecture-create.component';
import { LectureEditComponent} from "./event-dashboard/lecture-dashboard/lecture-edit/lecture-edit.component";
import { LecturesListComponent} from "./event-dashboard/lecture-dashboard/lectures-list/lectures-list.component";
import { SpeakersListComponent } from './event-dashboard/lecture-dashboard/speakers-list/speakers-list.component';
import { SpeakerCreateComponent } from './event-dashboard/lecture-dashboard/speaker-create/speaker-create.component';
import { SpeakerEditComponent } from './event-dashboard/lecture-dashboard/speaker-edit/speaker-edit.component';
import { LectureViewComponent } from './event-view/lecture-view/lecture-view.component';
import { SpeakerViewComponent } from './event-view/speaker-view/speaker-view.component';
import { CreateOpinionComponent } from './event-view/create-opinion/create-opinion.component';
import {ChartsModule} from "ng2-charts";
import '../../node_modules/chart.js/dist/Chart.bundle.min.js';
import {ApplicationStatisticsService} from "./application-statistics/application-statistics.service";
import { AccountMailModalComponent } from './accounts-administration/account-mail-modal/account-mail-modal.component';
import { EventMailModalComponent } from './events-administration/event-mail-modal/event-mail-modal.component';
import { EventWarnModalComponent } from './events-administration/event-warn-modal/event-warn-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    NavigationComponent,
    AlertComponent,
    routingComponents,
    UnauthorizedComponent,
    DemoComponent,
    LoginComponent,
    FooterComponent,
    FileSelectDirective,
    AccountComponent,
    HomeComponent,
    SearchComponent,
    RegisterUserComponent,
    EventViewComponent,
    RegisterParticipantComponent,
    EventCreateComponent,
    EventDashboardComponent,
    EventEditComponent,
    LectureEditComponent,
    StatisticsViewComponent,
    EventFormalsComponent,
    EventGuestsComponent,
    MyEventsViewComponent,
    AccountsAdministrationComponent,
    EventsAdministrationComponent,
    ApplicationStatisticsComponent,
    EventNavigationComponent,
    EventInfoComponent,
    LecturesListComponent,
    LectureDashboardComponent,
    LectureNavigationComponent,
    LectureCreateComponent,
    SpeakersListComponent,
    SpeakerCreateComponent,
    SpeakerEditComponent,
    LectureViewComponent,
    SpeakerViewComponent,
    CreateOpinionComponent,
    AccountMailModalComponent,
    EventMailModalComponent,
    EventWarnModalComponent,
    // DateTimePickerDirective,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    AuthenticationModule,
    AppRoutingModule,
    Ng2PageTransitionModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDzRwU70aqc9Fsin5RDv0MGpP12b-nnBGA'
    }),
    ChartsModule
  ],
  providers: [
    AuthenticationService,
    HttpSecService,
    routingGuards,
    CustomDateService,
    ApplicationStatisticsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

// export class SomeModule { }
