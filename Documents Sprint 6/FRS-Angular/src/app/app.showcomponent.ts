import {Component, OnInit, TemplateRef} from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
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

   
    modalRef: BsModalRef;
    flights:Flight[]=[];

    constructor(private service:FlightService, private router:Router,private modalService: BsModalService){}       

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




    openModalM(templateM: TemplateRef<any>) {
        this.modalRef = this.modalService.show(templateM);
      }
      openModalD(templateD: TemplateRef<any>) {
        this.modalRef = this.modalService.show(templateD);
      }


    
numberSortRevFlag=true;
numberSortFlag=false;

      sortByNumber(){
        this.seatSortFlag=false;
        this.nameSortFlag=false;
this.numberSortFlag=true;
this.flights.sort(
    (val1,val2)=>
    String(val1.flightNumber).localeCompare(String(val2.flightNumber))
    );

    this.numberSortRevFlag=!this.numberSortRevFlag;
    if(this.numberSortRevFlag){

        this.flights.reverse();
    }

      }
    
      nameSortRevFlag=true;
      nameSortFlag=false;
      
            sortByName(){
                this.seatSortFlag=false;
                this.numberSortFlag=false;
      this.nameSortFlag=true;
      this.flights.sort(
          (val1,val2)=>
          String(val1.carrierName).localeCompare(String(val2.carrierName))
          );
      
          this.nameSortRevFlag=!this.nameSortRevFlag;
          if(this.nameSortRevFlag){
      
              this.flights.reverse();
          }
      
            }

            seatSortRevFlag=true;
seatSortFlag=false;

      sortBySeat(){
        this.numberSortFlag=false;
        this.nameSortFlag=false;

this.seatSortFlag=true;
this.flights.sort(
    (val1,val2)=>
    String(val1.seatCapacity).localeCompare(String(val2.seatCapacity))
    );

    this.seatSortRevFlag=!this.seatSortRevFlag;
    if(this.seatSortRevFlag){

        this.flights.reverse();
    }

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