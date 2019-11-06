import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../_model/app.user';

//Author: Devang
//Description: Performs Authentication and user management operations
//Created On: 21/10/2019

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient:HttpClient) { }

  //Retrieves user token and checks authentication
  authenticate(username, password) {
    return this.httpClient.post<any>('http://localhost:9088/authenticate',
    {username,password}).subscribe(
         userData => {
          sessionStorage.setItem('username',username);
          let tokenStr= 'Bearer '+userData.token;
          sessionStorage.setItem('token', tokenStr);
          return userData;
         } 
      );
  }

  //Checks if user is logged in
  isUserLoggedIn():boolean {
    let user = sessionStorage.getItem('username')
    return !(user === null)
  }

  //Removes user session
  logOut() {
    sessionStorage.removeItem('username');
  }

  //Retrieves role of user
  getRole(username:String){
    return this.httpClient.get('http://localhost:9088/getRole?username='+username);
  }

  //Adds a new User
  signUp(user:User){
    return this.httpClient.post('http://localhost:9088/signup',user);
  }

}