import { Flight } from "./app.flight";
import { Schedule } from "./app.schedule";

export class ScheduleFlight{
    scheduleFlightId:number;
    flight:Flight;
    availableSeats:number;
    schedule:Schedule;
    ticketCost:number;
    scheduleFlightState:boolean;
}