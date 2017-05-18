import {City, EventType, Region, SimpleUser, Timestamp, User} from "./domainClass";
export class  EventSearchForm {
  siteNo:number;
  textContent:string;

  region:Region;
  city:City;
  eventType:EventType;

  dateFrom:Timestamp;
  dateTo:Timestamp;
  fromGeoWidth:string;
  toGeoWidth:string;
  fromGeoLenght:string;
  toGeoLenght:string;

  freeEntrance:boolean;
  registerEnabled:boolean;
}

export class  EventSearchResult {
  idEvent:number;
  user: SimpleUser;
  title:string;
  description:string;
  createDate:Timestamp;
  startTime:Timestamp;
  endTime:Timestamp;
  capacity:number;
  registeredGuests:number;
  freeEntrance:boolean;
  registerEnabled:boolean;
  visits:number;
  youtubeLink:string;
  geoLenght:string;
  geoWidth:string;

  geoNbLength:number;
  geoNbWidth:number;

  streetName:string;
  streetNo:string;
  eventType:string;
  cityName:string;
  regionName:string;
}

export class  EventToSearchForAdminForm {
  siteNo:number;
  textContent:string;
}

export class  EventForAdminResult {
  idEvent: number;
  title: string;
  cityname:string;
  startTime: Timestamp;
  endTime: Timestamp;
  capacity: number;
  published: boolean;
  register: boolean;
  registeredGuests:number;
  visits: number;
  user: User;
}

export class  UsersForAdminForm {
  siteNo:number;
  textContent:string;
}

export class  UsersForAdminResults {
  id: number;
  username: string;
  firstname: string;
  lastname: string;
  email: string;
  phone: string;
  enabled: boolean;
  lastpassres: Date;
}

export class StarsForAdminStatistics {
  eventTitle: string;
  star1: number;
  star2: number;
  star3: number;
  star4: number;
  star5: number;
}
