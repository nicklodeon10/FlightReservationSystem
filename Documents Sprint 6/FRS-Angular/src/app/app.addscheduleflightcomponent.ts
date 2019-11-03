import { Component,OnInit} from '@angular/core';
import {ScheduleFlightService} from './_service/app.scheduleflightservice';
import {ScheduleFlight} from './_model/app.scheduleflight';
import { ActivatedRoute, Router } from "@angular/router";
import { b } from '@angular/core/src/render3';

//Author: Surya
//Description: Adds schedule flights from flights
//Created On: 21/10/2019

@Component({
    selector : 'asf',
    templateUrl : '/_pages/app.addscheduledflight.html'
})


export class AddScheduleFlightComponent implements OnInit{

    sourceAirport:string;
    destinationAirport:string;
    departureDateTime:string;
    arrivalDateTime:string;

    minDate:any;
    maxDate:any;
    
    scheduleFlight:ScheduleFlight={scheduleFlightId:null, availableSeats:null, ticketCost:null,scheduleFlightState:true,flight:null,schedule:null};
    //schedule:Schedule={scheduleId:null,airport:"",departureDateTime:null,arrivalDateTime:null}
    //flight:Flight={flightNumber:null,flightModel:"",carrierName:"",seatCapacaity:null,flightState:true}
    //airport:Airport={airportName:"",airportCode:"",airportLocation=""}
    ngOnInit(){
        if(sessionStorage.getItem('role')==='user'){
            this.router.navigate(['noauth']);
          }   
    }

    
    constructor(private route: ActivatedRoute, private router:Router, private scheduleFlightService:ScheduleFlightService){
        this.minDate=new Date();
        this.maxDate=new Date();
        this.maxDate.setMonth(this.maxDate.getMonth()+2);
    }

   
    
   //service method call
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
        this.router.navigate(['adminpanel']);
    }

    buttonFlag:boolean=false;
    enableButton(){
        this.buttonFlag=!this.idValid&&!this.airportValid&&!this.costValid;
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

    airportValid:boolean=false;
    validateAirports(a:string, b:string){
        if(a.toLowerCase()===b.toLowerCase()){
            this.airportValid=true;
        }else{
            this.airportValid=false;
        }
    }

    costValid:boolean=false;
    validateCost(){
        if(this.scheduleFlight.ticketCost>=20000){
            this.costValid=true;
        }else{
            this.costValid=false;
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