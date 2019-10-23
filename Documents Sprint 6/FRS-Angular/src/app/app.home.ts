import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {Airport} from './_model/app.airport'
import { AirportService } from './_service/app.airportservice';

@Component({
    selector: 'home',
    templateUrl: '/_pages/app.home.html',
    styleUrls: ['../assets/css/home.css']
})
export class HomeComponent implements OnInit{

    airports:Airport[]=[];

    src:string;
    dest:string;
    journeyDate:string;

    minDate:any;
    maxDate:any;

    errorFlag:boolean=false;

    constructor(private router:Router, private airportService:AirportService){
        this.minDate=new Date();
        this.maxDate=new Date();
        this.maxDate.setMonth(this.maxDate.getMonth()+2);
    }

    ngOnInit(){
        this.airportService.getAllAirports().subscribe((data:Airport[])=>this.airports=data);
    }

    findFlights(){
        this.router.navigate(['/booking/availableflights', this.src, this.dest, this.journeyDate]);
    }

    airportCheck(){
        if(this.src===this.dest){
            this.errorFlag=true;
        }else{
            this.errorFlag=false;
        }
    }

}