import {Component, OnInit} from '@angular/core';
import { AuthenticationService } from './_service/app.authenticationservice';

//Author: Devang
//Description: Component for header
//Created On: 21/10/2019

@Component({
    selector: 'frsheader',
    templateUrl: '/_pages/app.header.html',
    styleUrls: ['../assets/css/header.css']
})
export class HeaderComponent implements OnInit{

    buttonFlag:boolean;
    username:string;
    user:boolean;
    admin:boolean;    

    constructor(private authenticationService:AuthenticationService){}

    ngOnInit(){
        this.user=false;
        this.admin=false;
        if(sessionStorage.getItem('role')==='user'){
            this.user=true;
        }else if(sessionStorage.getItem('role')==='admin'){
            this.admin=true;
        }
        this.buttonFlag=this.authenticationService.isUserLoggedIn();
        this.username=sessionStorage.getItem('username');
        if(this.username!=null)
            this.username=this.username.toUpperCase();
    }

}