import {Component, OnInit} from '@angular/core'
import { ScheduleFlight } from './_model/app.scheduleflight';
import { ScheduleFlightService } from './_service/app.scheduleflightservice';
import { Router } from '@angular/router';
/*import { Flight } from "./_model/app.flight";
import { Schedule } from "./_model/app.schedule";
import { AirportService } from './_service/app.airportservice';
import { Airport } from "./_model/app.airport";*/

//Author: Devang
//Description: Component for adding passenger details
//Created On: 21/10/2019


@Component({
    selector:'searchsf',
    templateUrl:'/_pages/app.searchscheduledflight.html'
})

export class SearchScheduleFlightComponent implements OnInit {
    

   
    /*scheduleFlightId:number;
    scheduleflight:ScheduleFlight={scheduleFlightId:null,availableSeats:null,scheduleFlightState:null,ticketCost:null,flight=null,schedule=null}
    airport:Airport={airportCode:null,airportLocation:"",airportName:""}
    schedule:Schedule={scheduleId:null,arrivalDateTime:null,departureDateTime:null,destinationAirport:"",sourceAirport:""}
    flight:Flight={flightNumber=null,carrierName:"",flightModel:"",flightState:null,seatCapacity:null}*/
    scheduleFlight:ScheduleFlight;
    scheduleFlightId:number;
    show:boolean=false;

    ngOnInit(){
        if(sessionStorage.getItem('role')==='user'){
            this.router.navigate(['noauth']);
          }
    this.scheduleFlight=new ScheduleFlight();    }
    constructor(private router:Router ,private service:ScheduleFlightService){

    }
    searchScheduleFlight(scheduleFlightId:number):any{
        this.show=true;
        console.log(scheduleFlightId);
        this.service.searchScheduleFlight(scheduleFlightId).subscribe((scheduleFlight:ScheduleFlight)=>this.scheduleFlight=scheduleFlight
        );

        /*validate():any{
            if(!this.scheduleFlight.scheduleFlightId!='' && this.scheduleFlight.scheduleFlightId.match("[0-9]")){
            this.status=true;
            }
            else{
            this.status=false;
            }
            */

    }
}