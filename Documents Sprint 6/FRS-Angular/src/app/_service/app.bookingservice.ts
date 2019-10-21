import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Booking } from '../_model/app.booking';
import { Passenger } from '../_model/app.passenger';

@Injectable({
    providedIn: 'root'
})
export class BookingService{

    constructor(private httpClient:HttpClient){}

    findFlights(src:string, dest:string, date:string){
        return this.httpClient.get("http://localhost:9088/booking/find?source_airport="+src+"&destination_airport="+dest+"&journey_date="+date);
    }

    addBooking(passengerList:Passenger[], flightId:any){
        let form=new FormData();
        //form.append("prodId", data.prodId);
        for(let i=0; i<passengerList.length; i++){
            form.append("passengerList["+i+"].passengerName", passengerList[i].passengerName);
            form.append("passengerList["+i+"].passengerAge", String(passengerList[i].passengerAge));
            form.append("passengerList["+i+"].passengerUIN", String(passengerList[i].passengerUIN));
        }
        return this.httpClient.post("http://localhost:9088/booking/add/?flightId="+flightId, form);
    }

}