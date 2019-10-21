import {Component, OnInit} from '@angular/core';
import { BookingService } from './_service/app.bookingservice';
import { Booking } from './_model/app.booking';
import {Router} from '@angular/router';

@Component({
    selector: 'confirmation',
    templateUrl: 'app.bookingconfirmation.html',
    styleUrls: ['../assets/css/bookingconfirmation.css']
})

export class BookingConfirmationComponent implements OnInit{

    prevBooking:Booking;
    userId:number=1;

    constructor(private router:Router ,private bookingService:BookingService){}

    ngOnInit(){
        this.bookingService.getPreviousBooking(this.userId).subscribe((data:Booking)=>this.prevBooking=data);
    }

    cancelTicket(bookingId:number){
        this.bookingService.cancelTicket(bookingId).subscribe();
        this.router.navigate(['showbookings']);
    }

    downloadTicket(bookingId:number){
        this.bookingService.downloadTicket(bookingId).subscribe();
    }

}