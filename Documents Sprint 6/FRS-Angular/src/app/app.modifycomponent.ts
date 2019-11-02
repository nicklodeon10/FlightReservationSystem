import {Component, OnInit} from '@angular/core';

import { ActivatedRoute, Router } from "@angular/router";
import { FlightService } from 'src/app/_service/app.flightservice';
import { Flight } from 'src/app/_model/app.flight';

//Author: Navya
//Description: Updates flight
//Created On: 21/10/2019

@Component({
    selector: 'modify',
    templateUrl: '/_pages/app.modify.html'
})

export class ModifyComponent implements OnInit{

   

    

    constructor(private service:FlightService, private route: ActivatedRoute,private router:Router){}       

    
      searchNumber:any;

      currFlight:Flight={"flightNumber": 0 ,"flightModel":"", "carrierName":"", "seatCapacity":0};

    ngOnInit() {
        if(sessionStorage.getItem('role')==='user'){
            this.router.navigate(['noauth']);
          }
        

        this.searchNumber=this.route.snapshot.paramMap.get("flightNumber");
        
            this.service.searchFlight(this.searchNumber).subscribe((flight:Flight)=> this.currFlight=flight);
           
            
        
            }


            modifyFlight(){

             this.service.modifyFlight(this.currFlight).subscribe();

             this.router.navigate(['/flight/view']);

            }

    
    
















            add(){

              this.router.navigate(['/flight/add']);
          
          }
          
          view(){
          
              this.router.navigate(['/flight/view']);
          
          }
          
          search(){
              
              this.router.navigate(['/flight/search']);
          
          }
          
          
          
          upload(){
          
            this.router.navigate(['/flight/upload']);
          }



}