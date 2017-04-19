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