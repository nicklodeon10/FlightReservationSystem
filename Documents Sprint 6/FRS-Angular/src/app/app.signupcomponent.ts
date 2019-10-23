import {Component, OnInit} from '@angular/core';
import { User } from './_model/app.user';
import { Router } from '@angular/router';

@Component({
    selector: 'signup',
    templateUrl: '/_pages/app.signup.html',
    styleUrls: ['../assets/css/signup.css']
})
export class SignUpComponent implements OnInit{
    
    user:User={"userId": null, "userName":"", "userPassword":"", "userPhone": null, "email":"", "active": null, "roles":""};

    constructor(private router:Router){}

    ngOnInit(){
        if(sessionStorage.getItem('role')==='user' || sessionStorage.getItem('role')==='admin'){
            this.router.navigate(['noauth']);
        }
    }

}