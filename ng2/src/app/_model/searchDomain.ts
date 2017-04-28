import {City, EventType, Region, SimpleUser, Timestamp} from "./domainClass";
export class EventSearchForm {
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

export class EventSearchResult {
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
  streetName:string;
  streetNo:string;
  eventType:string;
  cityName:string;
  regionName:string;
}





