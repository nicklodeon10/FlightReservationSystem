import {Component, OnInit} from '@angular/core';
import { Booking } from './_model/app.booking';
import { BookingService } from './_service/app.bookingservice';
import { saveAs } from 'file-saver';
import { Router } from '@angular/router';

//Author: Devang
//Description: Component for showing user details
//Created On: 21/10/2019

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

    //Cancels a ticket
    cancelTicket(bookingId:number){
        this.bookingService.cancelTicket(bookingId).subscribe();
        location.reload();
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
        console.log("Done");
    }

    depSortRevFlag=true;
    depSortFlag:boolean=false;
    sortByDeparture(){
        this.depSortFlag=true;
        this.dateSortFlag=false;
        this.bookingsList.sort(
            (val1, val2)=>
                String(val1.scheduleFlight.schedule.departureDateTime).localeCompare(String(val2.scheduleFlight.schedule.departureDateTime))
        );
        this.depSortRevFlag=!this.depSortRevFlag;
        if(this.depSortRevFlag){
            this.bookingsList.reverse();
        }
    }

    dateSortRevFlag=true;
    dateSortFlag:boolean=false;
    sortByBookingDate(){
        this.depSortFlag=false;
        this.dateSortFlag=true;
        this.bookingsList.sort(
            (val1, val2)=>
                String(val1.bookingDate).localeCompare(String(val2.bookingDate))
        );
        this.dateSortRevFlag=!this.dateSortRevFlag;
        if(this.dateSortRevFlag){
            this.bookingsList.reverse();
        }
    }

}