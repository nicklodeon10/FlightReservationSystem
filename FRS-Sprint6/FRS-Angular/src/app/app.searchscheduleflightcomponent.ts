  
import {Component, OnInit} from '@angular/core'
import { ScheduleFlight } from './_model/app.scheduleflight';
import { ScheduleFlightService } from './_service/app.scheduleflightservice';
import { Router } from '@angular/router';


//Author: Surya
//Description: Component for searching a schedule flight
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

    //service method call
    searchScheduleFlight(scheduleFlightId:number):any{
        this.show=true;
        console.log(scheduleFlightId);
        this.service.searchScheduleFlight(scheduleFlightId).subscribe((scheduleFlight:ScheduleFlight)=>this.scheduleFlight=scheduleFlight
        );
    }
    
    idValid:boolean=false;
    validateId(){
        if(this.scheduleFlight.scheduleFlightId>999){
            this.idValid=true;
        }
        else if(this.scheduleFlight.scheduleFlightId<1){
            this.idValid=true;
        }else{
            this.idValid=false;
        }
    }
        add(){

            this.router.navigate(['/scheduleFlight/add']);
        
        }
        
        view(){
        
            this.router.navigate(['/scheduleFlight/show']);
        
        }
        
        search(){
        
            this.router.navigate(['/scheduleFlight/search']);
        
        }

    
}