import {Component, OnInit} from '@angular/core';
import { Booking } from './_model/app.booking';
import { BookingService } from './_service/app.bookingservice';
import { saveAs } from 'file-saver';
import { Router } from '@angular/router';

@Component({
    selector: 'showbookings',
    templateUrl: '/_pages/app.showbookings.html',
    styleUrls: ['../assets/css/showbookings.css']
})
export class ShowBookingsComponent implements OnInit{

    public popoverTitle: string = 'Cancel Booking?';
    public popoverMessage: string = 'This action cannot be undone.';
    public confirmClicked: boolean = false;
    public cancelClicked: boolean = false;
    
    bookingsList:Booking[];
    userId:number;
    currDate:any;

    constructor(private router:Router, private bookingService:BookingService){ }

    ngOnInit(){
        if(sessionStorage.getItem('role')==='admin'){
            this.router.navigate(['noauth']);
        }
        this.userId=+sessionStorage.getItem('userId');
        this.bookingService.getBookingsByUser(this.userId).subscribe((data:Booking[])=>this.bookingsList=data);
        this.currDate=new Date();
    }

    cancelTicket(bookingId:number){
        this.bookingService.cancelTicket(bookingId).subscribe();
        location.reload();
    }

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
        console.log("Done");
    }

}