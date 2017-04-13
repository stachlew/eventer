import { Component, OnInit } from '@angular/core';
import {SafeResourceUrl, DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  ytUrl="http://www.youtube.com/embed/";
  videoUrl = "yyQ3YCbmcUU";

  url: SafeResourceUrl;

  constructor(sanitizer: DomSanitizer) {
    this.url = sanitizer.bypassSecurityTrustResourceUrl(this.ytUrl+this.videoUrl);
  }

  ngOnInit() {
  }

}
