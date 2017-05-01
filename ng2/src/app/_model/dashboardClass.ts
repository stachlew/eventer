import {EventType, Place, Timestamp} from "./domainClass";
export class EventDashboardAttendForm {
  idEvent:number;
  idParticipant:number;
  isPresent:boolean;
}


export class EventDashboardInfo {
  idEvent: number;
  capacity: number;
  description: string;
  endTime: Timestamp;
  freeEntrance: boolean;
  published: boolean;
  registerEnabled: boolean;
  startTime: Timestamp;
  title: string;
  visits: number;
  youtubeLink: string;
  place: Place;
  eventType: EventType;
}

export class EventDashboardStatisticsInfo {
  visits:number;
  participants:number;
  presence:number;
  stars5:number;
  stars4:number;
  stars3:number;
  stars2:number;
  stars1:number;
}

export class EventViewPartcipant {
  idPartcipant:number;
  activated: boolean;
  email: string;
  firstName: string;
  lastName: string;
  presence: boolean;
}
