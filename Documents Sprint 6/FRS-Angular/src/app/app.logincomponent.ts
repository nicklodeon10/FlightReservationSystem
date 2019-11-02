import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './_service/app.authenticationservice';
import { User } from './_model/app.user';

//Author: Devang
//Description: Component for login page
//Created On: 21/10/2019

@Component({
  selector: 'app-login',
  templateUrl: '/_pages/app.login.html',
  styleUrls: ['../assets/css/login.css']
})
export class LoginComponent implements OnInit {

  username = 'devang';
  password = '';
  user:User={"userId": 0, "userName":"", "userPassword":"", "userPhone": 0, "email":"", "active": null, "roles":""};
  invalidLogin = false;

  constructor(private router: Router,
    private loginservice: AuthenticationService) { }

  ngOnInit() {
  }

  //Checks user authentication
  checkLogin() {
    if (this.loginservice.authenticate(this.username, this.password)) {
      this.loginservice.getRole(this.username).subscribe((data:User)=>{this.user=data; this.redirect();});
    } else
      this.invalidLogin = true
  }

  //redirects according to user role
  redirect(){
    if(this.user.roles==="ROLE_USER"){
      sessionStorage.setItem('role','user');
      sessionStorage.setItem('userId',String(this.user.userId));
      this.invalidLogin = false;
      this.router.navigate(["/userpanel"]).then(()=>{
        window.location.reload();
      });
    }else if(this.user.roles==="ROLE_ADMIN"){
      sessionStorage.setItem('role','admin');
      sessionStorage.setItem('userId',String(this.user.userId));
      this.invalidLogin = false;
      this.router.navigate(["adminpanel"]).then(()=>{
        window.location.reload();
      }); 
    }
  }

}