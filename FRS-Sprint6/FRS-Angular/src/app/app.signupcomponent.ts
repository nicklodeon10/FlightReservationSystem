import {Component, OnInit} from '@angular/core';
import { User } from './_model/app.user';
import { Router } from '@angular/router';
import { AuthenticationService } from './_service/app.authenticationservice';

//Author: Devang
//Description: Component for adding new user
//Created On: 21/10/2019

@Component({
    selector: 'signup',
    templateUrl: '/_pages/app.signup.html',
    styleUrls: ['../assets/css/signup.css']
})
export class SignUpComponent implements OnInit{
    
    user:User={"userId": null, "userName":"", "userPassword":"", "userPhone": null, "email":"", "active": null, "roles":""};

    public barLabel: string = "Password strength:";
    public myColors = ['#DD2C00', '#FF6D00', '#FFD600', '#AEEA00', '#00C853'];

    constructor(private router: Router, private loginservice: AuthenticationService){}

    ngOnInit(){
        if(sessionStorage.getItem('role')==='user' || sessionStorage.getItem('role')==='admin'){
            this.router.navigate(['noauth']);
        }
    }

    //Adds a new user
    signUp(){
        console.log(this.user);
        this.loginservice.signUp(this.user).subscribe();
        this.router.navigate(["login"]);
    }

    //Validation
    nameFlag:boolean=false;
    validateName(){
        var flag = /^[a-zA-Z ]+$/.test(this.user.userName);
        if(!flag){
            this.nameFlag=true;
        }else{
            this.nameFlag=false;
        }
    }

    //Validation
    phoneFlag:boolean=false;
    validatePhone(){
        let phone=String(this.user.userPhone);
        if(phone.length!=10){
            this.phoneFlag=true;
        }else{
            this.phoneFlag=false;
        }
    }

    //Validation
    emailFlag:boolean=false;
    validateEmail(){
        var flag=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(this.user.email);
        if(!flag){
            this.emailFlag=true;
        }else{
            this.emailFlag=false;
        }
    }

    buttonFlag:boolean=true;
    enableButton(){
        this.buttonFlag=this.nameFlag||this.emailFlag||this.phoneFlag;
    }

}