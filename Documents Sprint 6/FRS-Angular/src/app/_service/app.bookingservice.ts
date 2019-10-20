import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class BookingService{

    constructor(private httpClient:HttpClient){}

    findFlights(src:string, dest:string, date:string){
        return this.httpClient.get("http://localhost:9088/booking/find?source_airport="+src+"&destination_airport="+dest+"&journey_date="+date);
    }

}