import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'userpanel',
    templateUrl: '/_pages/app.userpanel.html',
    styleUrls: ['../assets/css/userpanel.css']
})
export class UserPanelComponent implements OnInit{

    username:string;

    constructor(private router:Router){}

    ngOnInit(){
        if(sessionStorage.getItem('role')==='admin'){
            this.router.navigate(['noauth']);
        }
        this.username=sessionStorage.getItem('username');
        this.username=this.username.toUpperCase();
    }

}