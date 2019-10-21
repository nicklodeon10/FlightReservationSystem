import {Component, OnInit} from '@angular/core';
import { Booking } from './_model/app.booking';
import { BookingService } from './_service/app.bookingservice';

@Component({
    selector: 'showbookings',
    templateUrl: 'app.showbookings.html',
    styleUrls: ['../assets/css/showbookings.css']
})
export class ShowBookingsComponent implements OnInit{
    
    bookingsList:Booking[];
    userId:number=1;
    currDate:any;

    constructor(private bookingService:BookingService){ }

    ngOnInit(){
        this.bookingService.getBookingsByUser(this.userId).subscribe((data:Booking[])=>this.bookingsList=data);
        this.currDate=new Date();
    }

    cancelTicket(bookingId:number){
        this.bookingService.cancelTicket(bookingId).subscribe();
        location.reload();
    }

    downloadTicket(bookingId:number){
        this.bookingService.downloadTicket(bookingId).subscribe();
    }

}