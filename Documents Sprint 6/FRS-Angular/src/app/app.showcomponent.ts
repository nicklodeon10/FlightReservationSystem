import {Component, OnInit} from '@angular/core';

import {Router} from '@angular/router';
import { Flight } from 'src/app/_model/app.flight';
import { FlightService } from 'src/app/_service/app.flightservice';

//Author: Navya
//Description: view the flight details
//Created On: 21/10/2019

@Component({
    selector: 'show',
    templateUrl: '/_pages/app.show.html'
})

export class ShowComponent implements OnInit{

   

    flights:Flight[]=[];

    constructor(private service:FlightService, private router:Router){}       

    ngOnInit(){
        if(sessionStorage.getItem('role')==='user'){
            this.router.navigate(['noauth']);
          }
        this.service.showFlight().subscribe((data:Flight[])=>this.flights=data);
    }

    modifyFlight(flightNumber:number){
        this.router.navigate(['/flight/modify',flightNumber]);
       
    }

    deleteFlight(flightNumber: number):any{
        this.service.deleteFlight(flightNumber).subscribe();
        location.reload();
    }

















add(){

    this.router.navigate(['/flight/add']);

}

view(){

    this.router.navigate(['/flight/view']);

}





upload(){

  this.router.navigate(['/flight/upload']);
}


}