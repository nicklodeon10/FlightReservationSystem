import {Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";

@Component({
    selector: 'detail',
    templateUrl: 'app.bookingdetail.html',
    styleUrls: ['../assets/css/bookingdetail.css']
})
export class BookingDetailComponent implements OnInit{

    flightId:number;

    constructor(private route: ActivatedRoute, private router:Router){}

    ngOnInit(){
        this.flightId=+this.route.snapshot.paramMap.get("flightId");
    }

}