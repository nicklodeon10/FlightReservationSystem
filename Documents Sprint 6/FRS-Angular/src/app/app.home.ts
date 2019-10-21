import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {Airport} from './_model/app.airport'
import { AirportService } from './_service/app.airportservice';

@Component({
    selector: 'home',
    templateUrl: 'app.home.html',
    styleUrls: ['../assets/css/home.css']
})
export class HomeComponent implements OnInit{

    airports:Airport[]=[];

    src:string;
    dest:string;
    journeyDate:string;

    constructor(private router:Router, private airportService:AirportService){}

    ngOnInit(){
        this.airportService.getAllAirports().subscribe((data:Airport[])=>this.airports=data);
    }

    findFlights(){
        this.router.navigate(['/availableflights', this.src, this.dest, this.journeyDate]);
    }

}