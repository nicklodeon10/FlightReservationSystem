import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Airport } from '../_model/app.airport';

@Injectable({
    providedIn: 'root'
})

export class AirportService{

    constructor(private httpClient:HttpClient){}

    getAllAirports():Observable<Airport[]>{
        return this.httpClient.get<Airport[]>('http://localhost:9088/airport/getall');
    }

}