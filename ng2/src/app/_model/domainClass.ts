// Generated using typescript-generator version 1.9.205 on 2017-04-18 11:53:19.

// declare namespace Domain {
export class  Timestamp extends Date {
}

export class  Customer {
  id: number;
  name: string;
  email: string;
  date: Date;
}

export class  DemoClass {
  nr: number;
  napis: string;
}

export class AddEventClass {
  title: string;
  description: string;
  startTime: string;
  endTime: string;
  eventType: string;
  capacity: number;
  freeEntrance: boolean;

  region: string;
  city: string;
  streetName: string;
  streetNo: string;
  geoLength: string;
  geoWidth: string;
}

export class  Region {
  idRegion: number;
  regionName: string;
}

export class  City {
  idCity: number;
  cityName: string;
  region: Region;
}

export class  Place {
  idPlace: number;
  streetName: string;
  streetNo: string;
  geoLength: string;
  geoWidth: string;
  city: City;
}


export class  Document {
  idDocument: number;
  name: string;
  path: string;
  type: string;
}

export class  EventType {
  idEventType: number;
  eventTypeName: string;
}

export class  EventTypeDocument {
  idEventTypeDocument: number;
  eventType: EventType;
  document: Document;
}

export class  Template {
  idTemplate: number;
  templateName: string;
}

export class  EventViewSpeaker {
  idSpeaker: number;
  firstname: string;
  lastname: string;
  email: string;
  description: string;
  phone: string;
}


export class  EventViewLecture {
  idLecture: number;
  idEvent: number;

  lectureName: string;
  description: string;

  eventViewSpeaker: EventViewSpeaker;

  startTime: Timestamp;
  endTime: Timestamp;
}




export class EventViewDetails{
  idEvent: number;
  eventTypeName: string;
  eventStatus: string;
  username: string;

  title: string;
  description: string;

  regionName: string;
  cityName: string;
  streetName: string;
  streetNo: string;
  geoLength: string;
  geoWidth: string;

  youtubeLink: string;

  published: boolean;
  freeEntrance: boolean;
  registerEnabled: boolean;

  capacity: number;
  visits: number;

  startTime: Timestamp;
  endTime: Timestamp;
  createDate: Timestamp;

  lectures: EventViewLecture[];
  speakers: EventViewSpeaker[];
}

export class EventHeader{
  idEvent: number;
  title: string;
  description: string;
}


export class  Event {
  idEvent: number;
  title: string;
  description: string;
  place: Place;
  startTime: Timestamp;
  endTime: Timestamp;
  capacity: number;
  youtubeLink: string;
  published: boolean;
  freeEntrance: boolean;
  registerEnabled: boolean;
  visits: number;
  image: Blob;
  eventStatus: EventStatus;
  user: User;
  eventType: EventType;
  template: Template;
}

export class EventViewOpinion {
  idOpinion: number;
  idEvent: number;
  content: string;
  email: string;
  createDate: Timestamp;
}

export class EventViewOpinionForm {
  content: string;
  email: string;
  createDate: Timestamp;
  idEvent: number;
}




export class Opinion {
  idOpinion: number;
  content: string;
  email: string;
  createDate: Timestamp;
  event: Event;
}

export class  Participant {
  idParticipant: number;
  firstname: string;
  lastname: string;
  email: string;
  activated: boolean;
  presence: boolean;
  event: Event;
}

export class ParticipantForm{
  firstname:string;
  lastname:string
  email:string
  idEvent:number;
}


export class  Speaker {
  idSpeaker: number;
  firstname: string;
  lastname: string;
  email: string;
  description: string;
  phone: string;
  image: Blob;
}

export class  Lecture {
  idLecture: number;
  startTime: Timestamp;
  endTime: Timestamp;
  lectureName: string;
  description: string;
  speaker: Speaker;
  event: Event;
}

export class  User {
  id: number;
  username: string;
  password: string;
  firstname: string;
  lastname: string;
  email: string;
  phone: string;
  enabled: boolean;
  lastpassres: Date;
  authorities: Authority[];
}


export class  Authority {
  id: number;
  name: AuthorityName;
}




export class  AutoCloseable {
}

export class  Closeable extends AutoCloseable {
}

export class  InputStream extends Closeable {
}

export class  Blob {
        binaryStream: InputStream;
    }






    type EventStatus = "DELETED" | "UNPUBLISHED" | "PUBLISHED_AND_AVAILABLE" | "PUBLISHED_AND_NOT_AVAILABLE";

    type AuthorityName = "ROLE_USER" | "ROLE_ADMIN";

// }
