import { Component, OnInit } from '@angular/core';
import {Http, Response} from "@angular/http";
import {HttpSecService} from "../_service/util/http-sec.service";
import {Router} from "@angular/router";
import {ApplicationStatisticsService} from "./application-statistics.service";
import {StarsForAdminStatistics} from "../_model/searchDomain";

@Component({
  selector: 'app-application-statistics',
  templateUrl: './application-statistics.component.html',
  styleUrls: ['./application-statistics.component.css']
})

export class ApplicationStatisticsComponent implements OnInit{

  sumVisits: Array<any> = [];
  sumVisitsWYJSCIE: Array<any> = new Array(12);
  monthEvent: Array<any> = [];
  seePlots: boolean = false;
  createEvents: string = "";
  allVisitors: string = "";
  avgVisitors: string = "";
  activeUsers: string = "";
  allGuests: string = "";
  avgParticipant: string = "";
  countEventsInLastMonths: string = "";
  countEventsInThisMonths: string = "";

  public lineChartData:Array<any>;
  public lineChartLabels:Array<any>;
  public lineChartOptions:any;
  public lineChartColors:Array<any>;
  public lineChartLegend:boolean = true;
  public lineChartType:string;

  // radar chart
  public starsForAdminStatistics: StarsForAdminStatistics[] = new StarsForAdminStatistics()[5];
  public radarChartLabels:string[] = ['1','2','3','4','5'];
  public radarChartData:any = [{data: [0], label: ''},{data: [0], label: ''},{data: [0], label: ''},{data: [0], label: ''},{data: [0], label: ''}];
  public radarChartType:string = 'radar';
  public isRadarReady: boolean = false;

  constructor(private http: Http, private myHttp: HttpSecService, private router: Router, public statisticService: ApplicationStatisticsService) {
    this.lineChartData = [
      {data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], label: 'Ilość'}
    ];

    this.lineChartLabels = ['Styczeń', 'Luty', 'Marzec', 'Kwiecień', 'Maj', 'Czerwiec', 'Lipiec', 'Sierpień', 'Wrzesień', 'Październik', 'Listopad', 'Grudzień'];

    this.lineChartOptions = {
      responsive: true
    };

    this.lineChartColors = [
      {
        backgroundColor: 'rgba(148,159,177,0.2)',
        borderColor: 'rgba(148,159,177,1)',
        pointBackgroundColor: 'rgba(148,159,177,1)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgba(148,159,177,0.8)'
      }
    ];

    this.lineChartType = 'line';
  }

  ngOnInit() {
    this.getStatistic();
    this.statisticService.getRadarChartData().subscribe(data => this.convertRadarData(data));
    this.statisticService.getLineChartData().subscribe(data => this.convertLineData(data));
  }

  convertRadarData(data :StarsForAdminStatistics[]) {
    let i:number = 0;
    for (i; i < data.length; i++) {
      this.radarChartData[i] = {
        data: [
          data[i].star1,
          data[i].star2,
          data[i].star3,
          data[i].star4,
          data[i].star5],
        label: data[i].eventTitle
      }
    }
     this.isRadarReady = true;
  }

  convertLineData(data: number[]) {
    let chartData:Array<any> = new Array(12);
    let _chartData:Array<any> = new Array(12);
    chartData = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
    for (let chData of data) {
      chartData[chData[0]-1] = chData[1];
    }
    _chartData = [
          {data: [
            chartData[0],
            chartData[1],
            chartData[2],
            chartData[3],
            chartData[4],
            chartData[5],
            chartData[6],
            chartData[7],
            chartData[8],
            chartData[9],
            chartData[10],
            chartData[11]
          ], label: 'Liczba odwiedzin'}
        ];
    this.lineChartData = _chartData;
    this.seePlots = true;
  }

  getStatistic() {
    this.http.get(this.myHttp.getUrl() + '/api/administration/statistics/getCountOfEvents').subscribe((data: Response)=> this.createEvents = data.text());
    this.http.get(this.myHttp.getUrl() + '/api/administration/statistics/getCountOfAllVisits').subscribe((data: Response)=> this.allVisitors = data.text());
    this.http.get(this.myHttp.getUrl() + '/api/administration/statistics/getAverageOfAllVisits').subscribe((data: Response)=> this.avgVisitors = data.text());
    this.http.get(this.myHttp.getUrl() + '/api/administration/statistics/getCountOfAllActiveUsers').subscribe((data: Response)=> this.activeUsers = data.text());
    this.http.get(this.myHttp.getUrl() + '/api/administration/statistics/getCountParticipant').subscribe((data: Response)=> this.allGuests = data.text());
    this.http.get(this.myHttp.getUrl() + '/api/administration/statistics/getAvgParticipant').subscribe((data: Response)=> this.avgParticipant = data.text());
    this.http.get(this.myHttp.getUrl() + '/api/administration/statistics/getCountEventsInLastMonths').subscribe((data: Response)=> this.countEventsInLastMonths = data.text());
    this.http.get(this.myHttp.getUrl() + '/api/administration/statistics/getCountEventsInThisMonths').subscribe((data: Response)=> this.countEventsInThisMonths = data.text());
  }

}
