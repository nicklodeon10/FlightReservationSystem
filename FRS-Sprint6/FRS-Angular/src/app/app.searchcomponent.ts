import {Component, OnInit, TemplateRef} from '@angular/core';
import { Flight } from 'src/app/_model/app.flight';
import { FlightService } from 'src/app/_service/app.flightservice';
import { Router } from '@angular/router';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';


//Author: Navya
//Description: searches flight by it's id
//Created On: 21/10/2019

@Component({
    selector: 'search',
  templateUrl: '/_pages/app.search.html'
})

export class SearchComponent implements OnInit {
      flight: Flight;
      modalRef: BsModalRef;

    ngOnInit() {
      if(sessionStorage.getItem('role')==='user'){
        this.router.navigate(['noauth']);
      }
        this.flight = new Flight();
            }

    constructor(private service:FlightService, private router:Router,private modalService: BsModalService){}   
    searchFlight(flightNumber: number):any{
        this.service.searchFlight(flightNumber).subscribe((flight:Flight)=> this.flight=flight);
       
    }


    modifyFlight(flightNumber:number){
      this.router.navigate(['/flight/modify',flightNumber]);
     
  }

  deleteFlight(flightNumber: number):any{
      this.service.deleteFlight(flightNumber).subscribe();
      location.reload();
  }


    openModalM(templateM: TemplateRef<any>) {
      this.modalRef = this.modalService.show(templateM);
    }
    openModalD(templateD: TemplateRef<any>) {
      this.modalRef = this.modalService.show(templateD);
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
