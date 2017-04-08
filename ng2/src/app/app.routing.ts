import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AdministrationComponent} from './administration/administration.component';
import { SettingsComponent } from './settings/settings.component';
import { UnauthorizedComponent } from './_component/unauthorized/unauthorized.component';
import { AuthenticationGuard } from './_service/authentication/guard/authentication.guard';
import { RoleGuard } from './_service/authentication/guard/role.guard';
import { AccountComponent } from './account/account.component'

const appRoutes: Routes = [

  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'home',
    component: HomeComponent,
    //canActivate: [AuthenticationGuard] //Jakikolwiek zautoryzowany uzytkownik, brak rol
  },
  {
    path: 'administration',
    component: AdministrationComponent,
    canActivate: [AuthenticationGuard, RoleGuard],
    data: { roles : ['ROLE_ADMIN'] }
  },
  {
    path: 'settings',
    component: SettingsComponent,
    canActivate: [AuthenticationGuard, RoleGuard],
    data: { roles : ['ROLE_USER'] }
  },
  {
    path: 'account',
    component: AccountComponent,
    canActivate: [AuthenticationGuard, RoleGuard],
    data: { roles : ['ROLE_USER'] }
  },
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
  AdministrationComponent,
  SettingsComponent,
  UnauthorizedComponent,
  AccountComponent
];

export const routingGuards = [
  AuthenticationGuard,
  RoleGuard
];
