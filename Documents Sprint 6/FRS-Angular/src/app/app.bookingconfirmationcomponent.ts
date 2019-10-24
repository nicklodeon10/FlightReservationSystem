import {Component, OnInit} from '@angular/core';
import { BookingService } from './_service/app.bookingservice';
import { Booking } from './_model/app.booking';
import {Router, ActivatedRoute} from '@angular/router';
import { saveAs } from 'file-saver';

//Author: Devang
//Description: Confrmation Component for booking
//Created On: 21/10/2019

@Component({
    selector: 'confirmation',
    templateUrl: '/_pages/app.bookingconfirmation.html',
    styleUrls: ['../assets/css/bookingconfirmation.css']
})

export class BookingConfirmationComponent implements OnInit{

    prevBooking:Booking;
    prevBookingId:number;
    userId:number=1;

    constructor(private route:ActivatedRoute, private router:Router, private bookingService:BookingService){}

    ngOnInit(){
        if(sessionStorage.getItem('role')==='admin'){
            this.router.navigate(['noauth']);
        }
        this.prevBookingId=+this.route.snapshot.paramMap.get("bookingId");
        this.bookingService.getPreviousBooking(this.prevBookingId).subscribe((data:Booking)=>this.prevBooking=data);
    }

    //Cancels a ticket
    cancelTicket(bookingId:number){
        this.bookingService.cancelTicket(bookingId).subscribe();
        this.router.navigate(['showbookings']);
    }

    //Downloads a ticket
    downloadTicket(bookingId:number){
        this.bookingService.downloadTicket(bookingId).subscribe(
            response => {
                var blob = new Blob([response], {type: 'application/pdf'});
                var filename = 'ticket.pdf';
                saveAs(blob, filename);
            },
            error => {
                console.error(`Error: ${error.message}`);
            }
        );
    }

}