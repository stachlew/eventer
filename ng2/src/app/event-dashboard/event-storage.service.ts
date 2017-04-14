import { Injectable } from '@angular/core';

@Injectable()
export class EventStorageService {

  private currentEventId: string;

  constructor() { }


  getCurrentEventId(): string {
    return this.currentEventId;
  }

  setCurrentEventId(value: string) {
    this.currentEventId = value;
  }
}
