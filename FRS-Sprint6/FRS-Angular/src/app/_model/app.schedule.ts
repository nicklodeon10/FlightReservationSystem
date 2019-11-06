import { Airport } from "./app.airport";

export class Schedule{
    scheduleId:number;
    sourceAirport:Airport;
    destinationAirport:Airport;
    departureDateTime:any;
    arrivalDateTime:any;
}