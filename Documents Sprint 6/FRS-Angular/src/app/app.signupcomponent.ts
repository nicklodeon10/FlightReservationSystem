import {Component, OnInit} from '@angular/core';
import { User } from './_model/app.user';
import { Router } from '@angular/router';
import { AuthenticationService } from './_service/app.authenticationservice';

@Component({
    selector: 'signup',
    templateUrl: '/_pages/app.signup.html',
    styleUrls: ['../assets/css/signup.css']
})
export class SignUpComponent implements OnInit{
    
    user:User={"userId": null, "userName":"", "userPassword":"", "userPhone": null, "email":"", "active": null, "roles":""};

    constructor(private router: Router, private loginservice: AuthenticationService){}

    ngOnInit(){
        if(sessionStorage.getItem('role')==='user' || sessionStorage.getItem('role')==='admin'){
            this.router.navigate(['noauth']);
        }
    }

    signUp(){
        console.log(this.user);
        this.loginservice.signUp(this.user).subscribe();
        this.router.navigate(["login"]);
    }

    nameFlag:boolean=false;
    validateName(){
        var flag = /^[a-zA-Z ]+$/.test(this.user.userName);
        if(!flag){
            this.nameFlag=true;
        }else{
            this.nameFlag=false;
        }
    }

    phoneFlag:boolean=false;
    validatePhone(){
        let phone=String(this.user.userPhone);
        if(phone.length!=10){
            this.phoneFlag=true;
        }else{
            this.phoneFlag=false;
        }
    }

    emailFlag:boolean=false;
    validateEmail(){
        var flag=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(this.user.email);
        if(!flag){
            this.emailFlag=true;
        }else{
            this.emailFlag=false;
        }
    }

}