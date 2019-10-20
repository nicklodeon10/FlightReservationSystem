import {Component, OnInit} from '@angular/core';
import {Airport} from './_model/app.airport'
import { AirportService } from './_service/app.airportservice';
import { BookingService } from './_service/app.bookingservice';
import { ScheduleFlight } from './_model/app.scheduleflight';

@Component({
    selector: "availableflight",
    templateUrl: 'app.availableflight.html',
    styleUrls: ['../assets/css/availflight.css']
})

export class AvailableFlightComponent implements OnInit{

    airports:Airport[]=[];

    src:string;
    dest:string;
    journeyDate:string;
    displayFlag:boolean=false;
    scheduledFlights:ScheduleFlight[]=[];

    constructor(private airportService:AirportService, private bookingService:BookingService){}

    ngOnInit(){
        this.airportService.getAllAirports().subscribe((data:Airport[])=>this.airports=data);
    }

    findFlights(){
        this.displayFlag=true;
        this.bookingService.findFlights(this.src, this.dest, this.journeyDate).subscribe((data:ScheduleFlight[])=>this.scheduledFlights=data);
        console.log(this.scheduledFlights);
    }

}