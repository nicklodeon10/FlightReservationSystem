import {Component , OnInit} from '@angular/core';
import { Router } from '@angular/router';

//Author: Devang
//Description: Performs Authentication and user management operations
//Created On: 21/10/2019

@Component({
    selector: 'admin',
    templateUrl: '/_pages/app.adminpage.html'
})

export class AdminPageComponent implements OnInit {

    ngOnInit(){
        if(sessionStorage.getItem('role')==='user'){
            this.router.navigate(['noauth']);
          }
    }

    constructor( private router:Router){}  

    addFlight(){

    this.router.navigate(['flight/add']);

}
viewFlight(){

    this.router.navigate(['flight/view']);

}
searchFlight(){

    this.router.navigate(['flight/search']);

}

addScheduledFlight(){
    this.router.navigate(['scheduleFlight/add']);
}

viewScheduledFlight(){
    this.router.navigate(['scheduleFlight/show']);
}

searchScheduledFlight(){
    this.router.navigate(['scheduleFlight/search']);
}

}