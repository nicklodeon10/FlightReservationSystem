import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Airport } from '../_model/app.airport';

//Author: Devang
//Description: Performs Airport Service operations
//Created On: 21/10/2019

@Injectable({
    providedIn: 'root'
})

export class AirportService{

    constructor(private httpClient:HttpClient){}

    //Returns list of all airports
    getAllAirports():Observable<Airport[]>{
        return this.httpClient.get<Airport[]>('http://localhost:9088/airport/getall');
    }

}