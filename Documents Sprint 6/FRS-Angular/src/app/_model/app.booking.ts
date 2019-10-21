import { ScheduleFlight } from "./app.scheduleflight";
import { Passenger } from "./app.passenger";

export class Booking{
    
    bookingId:number;
    userId:number;
    bookingDate:any;
    ticketCost:number;
    passengerCount:number;
    bookingState:boolean;
    scheduleFlight:ScheduleFlight;
    passengerList:Passenger[];

}