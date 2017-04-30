import { Injectable } from '@angular/core';

@Injectable()
export class CustomDateService {

  constructor() { }

  public convDatePickerToTimestamp(argDate: Date){
    let date = new Date(argDate);
    let str = '';
    let year, month, day, hour, min;
    year = date.getUTCFullYear();
    month = date.getUTCMonth();
    month = month < 10 ? '0' + month : month;
    day = date.getUTCDate();
    day = day < 10 ? '0' + day : day;
    //hour = date.getUTCHours();
    hour = date.getHours();
    hour = hour < 10 ? '0' + hour : hour;
    //min = date.getUTCMinutes();
    min = date.getMinutes();
    min = min < 10 ? '0' + min : min;
    str += year + '-' + month + '-' + day;
    str += ' ' + hour + ':' + min;
    let timeSt = new Date(year,month,day,hour,min);
    return timeSt;
  }

}
