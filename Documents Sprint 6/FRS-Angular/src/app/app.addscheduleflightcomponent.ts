import { Component,OnInit} from '@angular/core';
import {ScheduleFlightService} from './_service/app.scheduleflightservice';
import {ScheduleFlight} from './_model/app.scheduleflight';
import { ActivatedRoute, Router } from "@angular/router";

@Component({
    selector : 'asf',
    templateUrl : '/_pages/app.addscheduledflight.html'
})


export class AddScheduleFlightComponent implements OnInit{

    sourceAirport:string;
    destinationAirport:string;
    departureDateTime:string;
    arrivalDateTime:string;
    scheduleFlight:ScheduleFlight={scheduleFlightId:null, availableSeats:null, ticketCost:null,scheduleFlightState:true,flight:null,schedule:null};
    //schedule:Schedule={scheduleId:null,airport:"",departureDateTime:null,arrivalDateTime:null}
    //flight:Flight={flightNumber:null,flightModel:"",carrierName:"",seatCapacaity:null,flightState:true}
    //airport:Airport={airportName:"",airportCode:"",airportLocation=""}
    ngOnInit(){
        if(sessionStorage.getItem('role')==='user'){
            this.router.navigate(['noauth']);
          }   
    }

    
    constructor(private route: ActivatedRoute, private router:Router, private scheduleFlightService:ScheduleFlightService){}

   
    
   
    addScheduleFlight(scheduleFlight,sa,da,ddt,adt){
        alert(sa+da+ ddt+ adt);
        this.sourceAirport=sa;
        this.destinationAirport=da;
        this.departureDateTime=ddt;
        this.arrivalDateTime=adt;
        this.scheduleFlightService.addScheduleFlight( scheduleFlight,sa,da,ddt,adt).subscribe();
        //this.service.addScheduleFlight(this.model).subscribe((data)=>this.scheduleFlight=data);
        this.router.navigate(['Adding confirmation']);
        alert("ScheduleFlight Added");
        location.reload();
    }
    
}