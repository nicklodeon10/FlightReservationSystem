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
    public popoverTitle: string = 'Cancel Flight?';
    public popoverMessage: string = 'This action cannot be undone.';
    public confirmClicked: boolean = false;
    public cancelClicked: boolean = false;

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
    add(){

        this.router.navigate(['/scheduleFlight/add']);
    
    }
    
    view(){
    
        this.router.navigate(['/scheduleFlight/show']);
    
    }
    
    search(){
    
        this.router.navigate(['/scheduleFlight/search']);
    
    }
    IdSortRev:boolean=true;
    IdSort:boolean;
    sortById(){
        this.IdSort=true;
        this.scheduleFlights.sort(
            (val1, val2)=>
            val1.scheduleFlightId-val2.scheduleFlightId  
        );
        this.IdSortRev=!this.IdSortRev;
        if(this.IdSortRev){
            this.scheduleFlights.reverse();
        }
    }
    costSortRev:boolean=true;
    costSort:boolean;
    sortByCost(){
        this.costSort=true;
        
        this.scheduleFlights.sort(
            (val1, val2)=>
            val1.ticketCost-val2.ticketCost  
        );
        this.costSortRev=!this.costSortRev;
        if(this.costSortRev){
            this.scheduleFlights.reverse();
        }
    }
    
   
    
}