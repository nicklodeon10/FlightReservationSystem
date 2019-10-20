import {Component, OnInit} from '@angular/core';
import {Airport} from './_model/app.airport'
import { AirportService } from './_service/app.airportservice';

@Component({
    selector: "availableflight",
    templateUrl: 'app.availableflight.html',
    styleUrls: ['../assets/css/availflight.css']
})

export class AvailableFlightComponent implements OnInit{

    airports:Airport[]=[];

    constructor(private airportService:AirportService){}

    ngOnInit(){
        this.airportService.getAllAirports().subscribe((data:Airport[])=>this.airports=data);
    }

}