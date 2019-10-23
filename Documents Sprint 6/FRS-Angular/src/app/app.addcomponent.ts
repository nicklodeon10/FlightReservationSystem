import {Component, OnInit} from '@angular/core';


import { Router } from '@angular/router';
import { Flight } from 'src/app/_model/app.flight';
import { FlightService } from 'src/app/_service/app.flightservice';

// import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
// import { AbstractControl } from '@angular/forms';

@Component({
    selector: 'add',
  templateUrl: '/_pages/app.add.html'
})

export class AddComponent implements OnInit {
      flight: Flight;
      model:Flight;
      // myForm: FormGroup;
     
    ngOnInit() {
      if(sessionStorage.getItem('role')==='user'){
        this.router.navigate(['noauth']);
      }
        this.flight = new Flight();
        this.model = new Flight();   
      }

    constructor(private service:FlightService, private router:Router){}   
    addFlight(){ 
        this.service.addFlight(this.model).subscribe((data)=> this.flight=data);  
         
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

}
