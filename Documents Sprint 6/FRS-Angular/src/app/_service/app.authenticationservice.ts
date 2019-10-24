import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../_model/app.user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient:HttpClient) { }

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

  isUserLoggedIn():boolean {
    let user = sessionStorage.getItem('username')
    return !(user === null)
  }

  logOut() {
    sessionStorage.removeItem('username');
  }

  getRole(username:String){
    return this.httpClient.get('http://localhost:9088/getRole?username='+username);
  }

  signUp(user:User){
    return this.httpClient.post('http://localhost:9088/signup',user);
  }

}