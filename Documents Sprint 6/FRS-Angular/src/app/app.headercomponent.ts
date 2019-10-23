import {Component, OnInit} from '@angular/core';
import { AuthenticationService } from './_service/app.authenticationservice';

@Component({
    selector: 'frsheader',
    templateUrl: 'app.header.html',
    styleUrls: ['../assets/css/header.css']
})
export class HeaderComponent implements OnInit{

    buttonFlag:boolean;

    constructor(private authenticationService:AuthenticationService){}

    ngOnInit(){
        this.buttonFlag=this.authenticationService.isUserLoggedIn();
        console.log(sessionStorage.getItem('token'));
    }

}