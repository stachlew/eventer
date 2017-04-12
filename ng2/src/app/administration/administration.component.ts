import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-administration',
  templateUrl: './administration.component.html',
  styleUrls: ['./administration.component.css']
})
export class AdministrationComponent implements OnInit {

  //mapy
  zoom: number = 12;
  lat: number = 52.25353;
  lng: number = 20.90067;

  clickedMarker(label: string, index: number) {
    console.log(`clicked the marker: ${label || index}`)
  }

  mapClicked($event: any) {
    this.markers.push({
      lat: $event.coords.lat,
      lng: $event.coords.lng,
      draggable: true
    });
    console.info("sze/dlu "+$event.coords.lat+" "+$event.coords.lng);
  }

  markerDragEnd(m: marker, $event: any) {
    m.lat = $event.coords.lat;
    m.lng = $event.coords.lng;
    console.log('dragEnd', m, $event);
  }

  markers: marker[] = [
    {
      lat: 52.238907,
      lng: 20.944372,
      label: 'K',
      draggable: true
    },
    {
      lat: 52.229892,
      lng: 20.896103,
      label: 'P',
      draggable: false
    },
    {
      lat: 52.239127,
      lng: 20.897972,
      label: 'M',
      draggable: true
    }
  ]

  constructor() { }

  ngOnInit() {
  }



}

interface marker {
  lat: number;
  lng: number;
  label?: string;
  draggable: boolean;
}
