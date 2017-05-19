export interface User {
  username: string;
  firstname: string;
  lastname: string;
  email: string;
  authorities: string[];
  enabled: boolean;
}

export class UserRegisterForm {
  username:string;
  firstname:string;
  lastname:string;
  email:string;
  phone:string;
}

export class UserChangeForm {
  username:string;
  email:string;

  //zmienne
  firstname:string;
  lastname:string;
  phone:string;

  oldPass:string;
  newPass:string;
}
