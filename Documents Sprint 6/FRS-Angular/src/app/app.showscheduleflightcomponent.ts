import {Component,OnInit} from '@angular/core';
import {ScheduleFlightService} from './_service/app.scheduleflightservice';
import {ScheduleFlight} from './_model/app.scheduleflight';

import {Router} from '@angular/router';
//import { routerNgProbeToken } from '@angular/router/src/router_module';

//Author: Surya
//Description: Component for viewing and removing schedule flights
//Created On: 21/10/2019

@Component({
    selector:'show',
    templateUrl:'./_pages/app.showscheduleflight.html'
})

export class ShowScheduleFlightComponent implements OnInit{
    scheduleFlights:ScheduleFlight[]=[];
    
    
   
    constructor(private service:ScheduleFlightService,private router:Router){
        //console.log("NIn gghc in constructor")
       }

       //service method call for viewing
    ngOnInit() {
        if(sessionStorage.getItem('role')==='user'){
            this.router.navigate(['noauth']);
          }
        this.service.showScheduleFlights().subscribe((data:ScheduleFlight[])=>this.scheduleFlights=data);
        //alert("inside init method of show");
        
    }
    
    /*modifyScheduleFlight(scheduleFlightId:number){
        this.router.navigate(['/scheduleFlight/modify',scheduleFlightId]);
            
    }*/

    //service method call for removing schedule lfights
    removeScheduleFlight(scheduleFlightId:number):any{
        this.service.removeScheduleFlight(scheduleFlightId).subscribe(); 
        location.reload();
    }
   
    
}