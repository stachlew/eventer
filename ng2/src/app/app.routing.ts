import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DemoComponent } from './demo/demo.component';
import { UnauthorizedComponent } from './_component/unauthorized/unauthorized.component';
import { AuthenticationGuard } from './_service/authentication/guard/authentication.guard';
import { RoleGuard } from './_service/authentication/guard/role.guard';
import { AccountComponent } from './account/account.component'
import {AccountsAdministrationComponent} from "./accounts-administration/accounts-administration.component";
import {EventsAdministrationComponent} from "./events-administration/events-administration.component";
import {ApplicationStatisticsComponent} from "./application-statistics/application-statistics.component";
import {MyEventsViewComponent} from "./my-events-view/my-events-view.component";
import {EventCreateComponent} from "./event-create/event-create.component";
import {EventDashboardComponent} from "./event-dashboard/event-dashboard.component";
import {EventEditComponent} from "./event-dashboard/event-edit/event-edit.component";
import {EventFormalsComponent} from "./event-dashboard/event-formals/event-formals.component";
import {EventGuestsComponent} from "./event-dashboard/event-guests/event-guests.component";
import {StatisticsViewComponent} from "./event-dashboard/statistics-view/statistics-view.component";
import {EventViewComponent} from "./event-view/event-view.component";
import {RegisterParticipantComponent} from "./event-view/register-participant/register-participant.component";
import {SearchComponent} from "./search/search.component";
import {RegisterUserComponent} from "./register-user/register-user.component";
import {HomeComponent} from "./home/home.component";
import {EventInfoComponent} from "./event-dashboard/event-info/event-info.component";
import {LectureDashboardComponent} from "./event-dashboard/lecture-dashboard/lecture-dashboard.component";
import {LectureCreateComponent} from "./event-dashboard/lecture-dashboard/lecture-create/lecture-create.component";
import {LecturesListComponent} from "./event-dashboard/lecture-dashboard/lectures-list/lectures-list.component";
import {LectureEditComponent} from "./event-dashboard/lecture-dashboard/lecture-edit/lecture-edit.component";
import {SpeakersListComponent} from "./event-dashboard/lecture-dashboard/speakers-list/speakers-list.component";
import {SpeakerEditComponent} from "./event-dashboard/lecture-dashboard/speaker-edit/speaker-edit.component";
import {SpeakerCreateComponent} from "./event-dashboard/lecture-dashboard/speaker-create/speaker-create.component";

const appRoutes: Routes = [

  //GOSC
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'demo',
    component: DemoComponent
  },
  {
    path: 'event/view/:id',
    component: EventViewComponent
  },
  {
    path: 'event/register',
    component: RegisterParticipantComponent
  },
  {
    path: 'search',
    component: SearchComponent
  },
  {
    path: 'register',
    component: RegisterUserComponent
  },
  //USER
  {
    path: 'account',
    component: AccountComponent,
    canActivate: [AuthenticationGuard, RoleGuard],
    data: { roles : ['ROLE_USER'] }
  },
  {
    path: 'event/my',
    component: MyEventsViewComponent,
    canActivate: [AuthenticationGuard, RoleGuard],
    data: { roles : ['ROLE_USER'] }
  },
  {
    path: 'event/create',
    component: EventCreateComponent,
    canActivate: [AuthenticationGuard, RoleGuard],
    data: { roles : ['ROLE_USER'] }
  },

  //DASHBOARD
  {
    path: 'event/dashboard/:id',
    component: EventDashboardComponent,
    canActivate: [AuthenticationGuard, RoleGuard],
    data: { roles : ['ROLE_USER'] },
    children: [
      {path: '', component: EventInfoComponent},
      {path: 'info', component: EventInfoComponent},
      {path: 'edit', component: EventEditComponent},
      {path: 'formals', component: EventFormalsComponent},
      {path: 'guests', component: EventGuestsComponent},
      {path: 'statistics', component: StatisticsViewComponent},
      {path: 'lectures', component: LectureDashboardComponent,
      children: [
        {path: '', component: LecturesListComponent},
        {path: 'edit/:id', component: LectureEditComponent},

        {path: 'create/lecture', component: LectureCreateComponent},
        {path: 'create/speaker', component: SpeakerCreateComponent},

        {path: 'speakers', component: SpeakersListComponent},
        {path: 'speakers/edit/:id', component: SpeakerEditComponent},
      ]},
    ]
  },
  //END: DASHBOARD


  //ADMIN:
  {
    path: 'administration/users',
    component: AccountsAdministrationComponent,
    canActivate: [AuthenticationGuard, RoleGuard],
    data: { roles : ['ROLE_ADMIN'] }
  },
  {
    path: 'administration/events',
    component: EventsAdministrationComponent,
    canActivate: [AuthenticationGuard, RoleGuard],
    data: { roles : ['ROLE_ADMIN'] }
  },
  {
    path: 'administration/statistics',
    component: ApplicationStatisticsComponent,
    canActivate: [AuthenticationGuard, RoleGuard],
    data: { roles : ['ROLE_ADMIN'] }
  },
  //INNE:
  {
    path: 'unauthorized', //w przypadku wejscia na jakas strone przy braku uprawnien
    component: UnauthorizedComponent
  },
  {
    path: '', //strona glowna
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: '**', //kazdy brakujacy powyzej
    redirectTo: 'home'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule {}

export const routingComponents = [
  LoginComponent,
  HomeComponent,
  UnauthorizedComponent,
  AccountComponent
];

export const routingGuards = [
  AuthenticationGuard,
  RoleGuard
];
