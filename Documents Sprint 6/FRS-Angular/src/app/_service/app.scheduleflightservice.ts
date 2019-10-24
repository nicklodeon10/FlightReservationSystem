import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { ScheduleFlight } from '../_model/app.scheduleflight';

//Author: Devang
//Description: Performs Authentication and user management operations
//Created On: 21/10/2019

@Injectable({
   providedIn:'root' 
})

export class ScheduleFlightService{
    constructor(private myhttp:HttpClient){}

    showScheduleFlights(){
       
      return this.myhttp.get("http://localhost:9088/scheduleFlight/showdata");
    }

    addScheduleFlight(scheduleFlight:ScheduleFlight,sourceAirport,destinationAirport,departureDateTime,arrivalDateTime){
      let form=new FormData();
        form.append("scheduleFlightId", String(scheduleFlight.scheduleFlightId));
        form.append("ticketCost", String(scheduleFlight.ticketCost));
        let params = new HttpParams()
        .set('source_airport', sourceAirport)
        .set('destination_airport', destinationAirport)
        .set('departure_time', departureDateTime)
        .set('arrival_time', arrivalDateTime);

        console.log(params.toString());
        

        return this.myhttp.post('http://localhost:9088/scheduleFlight/add?',form,{params}); 
      
     
    }
    
    modifyScheduleFlight(scheduleFlightId:number){
   
        return this.myhttp.put("http://localhost:9088/scheduleFlight/modify?scheduleFlightId=",scheduleFlightId);
    }
    searchScheduleFlight(scheduleFlightId:number){
        
        return this.myhttp.get("http://localhost:9088/scheduleFlight/search?scheduleFlightId="+scheduleFlightId);
    }

    removeScheduleFlight(scheduleFlightId:number){
       return this.myhttp.delete("http://localhost:9088/scheduleFlight/delete?scheduleFlightId="+scheduleFlightId);
    }
}