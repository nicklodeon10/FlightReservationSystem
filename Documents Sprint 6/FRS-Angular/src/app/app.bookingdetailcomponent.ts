import {Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { BookingService } from './_service/app.bookingservice';
import { Booking } from './_model/app.booking';
import { Passenger } from './_model/app.passenger';

@Component({
    selector: 'detail',
    templateUrl: 'app.bookingdetail.html',
    styleUrls: ['../assets/css/bookingdetail.css']
})
export class BookingDetailComponent implements OnInit{

    flightId:number;
    booking:Booking={bookingId:null, bookingDate:null, bookingState:null, passengerCount:null, userId:null, ticketCost:null, scheduleFlight:null, passengerList:null};
    passengerList:Passenger[]=[{pnrNumber: null, passengerName:"", passengerAge:null, passengerUIN:null, passengerState:true}];
    passengerCount:number=1;

    constructor(private route: ActivatedRoute, private router:Router, private bookingService:BookingService){}

    ngOnInit(){
        this.flightId=+this.route.snapshot.paramMap.get("flightId");
    }

    addPassenger(){
        if(this.passengerCount<=3){
            this.passengerList.push({pnrNumber: null, passengerName:"", passengerAge:null, passengerUIN:null, passengerState:true});
            this.passengerCount++;
        }
    }

    removePassenger(){
        if(this.passengerCount>1){
            this.passengerList.pop();
            this.passengerCount--;
        }
    }

    addBooking(){
        this.booking.passengerList=this.passengerList;
        this.bookingService.addBooking(this.passengerList, this.flightId).subscribe();
    }

}