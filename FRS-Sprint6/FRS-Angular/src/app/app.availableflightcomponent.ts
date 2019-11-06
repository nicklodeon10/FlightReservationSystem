import {Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import {Airport} from './_model/app.airport'
import { AirportService } from './_service/app.airportservice';
import { BookingService } from './_service/app.bookingservice';
import { ScheduleFlight } from './_model/app.scheduleflight';

//Author: Devang
//Description: Component for showing available flights for booking to user
//Created On: 21/10/2019

@Component({
    selector: "availableflight",
    templateUrl: '/_pages/app.availableflight.html',
    styleUrls: ['../assets/css/availflight.css']
})

export class AvailableFlightComponent implements OnInit{

    airports:Airport[]=[];

    src:string;
    dest:string;
    journeyDate:string;
    displayFlag:boolean=false;
    scheduledFlights:ScheduleFlight[]=[];

    pageconfig: any;

    minDate:any;
    maxDate:any;

    errorFlag:boolean=false;
    buttonFlag:boolean=true;

    constructor(private route: ActivatedRoute, private router:Router, private airportService:AirportService, private bookingService:BookingService){
        this.minDate=new Date();
        this.maxDate=new Date();
        this.maxDate.setMonth(this.maxDate.getMonth()+2);
    }

    ngOnInit(){
        if(sessionStorage.getItem('role')==='admin'){
            this.router.navigate(['noauth']);
        }
        this.src=this.route.snapshot.paramMap.get("src");
        this.dest=this.route.snapshot.paramMap.get("dest");
        this.journeyDate=this.route.snapshot.paramMap.get("doj");
        if(this.src!=null && this.dest!=null && this.journeyDate!=null){
            this.findFlights();
        }
        this.airportService.getAllAirports().subscribe((data:Airport[])=>this.airports=data);
    }

    //Finds flights to book
    findFlights(){
        this.displayFlag=true;
        this.bookingService.findFlights(this.src, this.dest, this.journeyDate).subscribe((data:ScheduleFlight[])=>this.scheduledFlights=data);
    }

    //Routes to enter details page
    enterDetails(flightId:number){
        this.router.navigate(['/booking/enterdetails', flightId]);
    }

    //Validation
    airportCheck(){
        if(this.src===this.dest){
            this.errorFlag=true;
        }else{
            this.errorFlag=false;
        }
    }
    
    //Validation
    enableButton(){
        this.buttonFlag=this.errorFlag;
    }

    costSortRev:boolean=true;
    costSort:boolean;
    sortByCost(){
        this.costSort=true;
        this.depSort=false;
        this.scheduledFlights.sort(
            (val1, val2)=>
            val1.ticketCost-val2.ticketCost  
        );
        this.costSortRev=!this.costSortRev;
        if(this.costSortRev){
            this.scheduledFlights.reverse();
        }
    }

    depSortRev:boolean=true;
    depSort:boolean;
    sortByDeparture(){
        this.depSort=true;
        this.costSort=false;
        this.scheduledFlights.sort(
            (val1, val2)=>    
            String(val1.schedule.departureDateTime).localeCompare(String(val2.schedule.departureDateTime))
        );
        this.depSortRev=!this.depSortRev;
        if(this.depSortRev){
            this.scheduledFlights.reverse();
        }
    }
}