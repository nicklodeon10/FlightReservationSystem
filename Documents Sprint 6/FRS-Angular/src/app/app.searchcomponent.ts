import {Component, OnInit} from '@angular/core';
import { Flight } from 'src/app/_model/app.flight';
import { FlightService } from 'src/app/_service/app.flightservice';
import { Router } from '@angular/router';

//Author: Devang
//Description: Component for adding passenger details
//Created On: 21/10/2019

@Component({
    selector: 'search',
  templateUrl: '/_pages/app.search.html'
})

export class SearchComponent implements OnInit {
      flight: Flight;
      

    ngOnInit() {
      if(sessionStorage.getItem('role')==='user'){
        this.router.navigate(['noauth']);
      }
        this.flight = new Flight();
            }

    constructor(private service:FlightService, private router:Router){}   
    searchFlight(flightNumber: number):any{
        this.service.searchFlight(flightNumber).subscribe((flight:Flight)=> this.flight=flight);
       
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

}
