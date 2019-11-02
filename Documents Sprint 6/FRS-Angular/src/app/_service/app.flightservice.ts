import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Flight } from 'src/app/_model/app.flight';

//Author: Navya
//Description: Performs flight service 
//Created On: 21/10/2019

@Injectable({
    providedIn: 'root'
})




export class FlightService{

    constructor(private myhttp:HttpClient){}



//adding flight data
    addFlight(data:Flight):Observable<Flight>{
        console.log(data);
        let form=new FormData();
        form.append("flightModel", data.flightModel);
        form.append("carrierName", data.carrierName);
        form.append("seatCapacity", String(data.seatCapacity));

        return this.myhttp.post<Flight>('http://localhost:9088/flight/add',form);
        
        }


// to view flight data
        showFlight(){

            return this.myhttp.get('http://localhost:9088/flight/view');
        }

// to update flight data
        modifyFlight(data:any){
            
            return this.myhttp.put('http://localhost:9088/flight/modify',data);
            
        }


    // to search flight data
        searchFlight(fNum:number){
            
            return this.myhttp.get('http://localhost:9088/flight/search?flightNumber='+fNum);
        }

        //to delete flight data
        deleteFlight(flightNumber:number){

            return this.myhttp.delete('http://localhost:9088/flight/delete?flightNumber='+flightNumber);
        }

}

