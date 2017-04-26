import {Timestamp} from "../_model/domainClass";
export class AddEventClass {

  title: string;
  description: string;
  startTime: Timestamp;
  endTime: Timestamp;
  eventType: string;
  capacity: number;
  freeEntrance: boolean;

  region: string;
  city: string;
  streetName: string;
  streetNo: string;
  geoLength: string;
  geoWidth: string;

  constructor(){}
}
