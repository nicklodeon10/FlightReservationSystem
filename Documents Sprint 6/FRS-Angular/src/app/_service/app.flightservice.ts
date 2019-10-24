import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Flight } from 'src/app/_model/app.flight';

//Author: Devang
//Description: Performs Authentication and user management operations
//Created On: 21/10/2019

@Injectable({
    providedIn: 'root'
})




export class FlightService{

    constructor(private myhttp:HttpClient){}




    addFlight(data:any):Observable<Flight>{

        return this.myhttp.post<Flight>('http://localhost:9088/flight/add',data);
        
        }



        showFlight(){

            return this.myhttp.get('http://localhost:9088/flight/view');
        }


        modifyFlight(data:any){

            return this.myhttp.put('http://localhost:9088/flight/modify',data);
        }

        searchFlight(flightNumber:number){

            return this.myhttp.get('http://localhost:9088/flight/search?flightNumber='+flightNumber);
        }

        deleteFlight(flightNumber:number){

            return this.myhttp.delete('http://localhost:9088/flight/delete?flightNumber='+flightNumber);
        }

}

