import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { rootRenderNodes } from '@angular/core/src/view';

@Injectable({
    providedIn: 'root'
})
export class AirportService{

    constructor(private httpClient:HttpClient){}

    getAllAirports(){
        return this.httpClient.get('http://localhost:9088/airport/getall');
    }

}