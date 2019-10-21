import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from "@angular/forms";
import { AppComponent }  from './app.component';
import { HomeComponent } from './app.home';
import { HttpClientModule } from '@angular/common/http';
import { AvailableFlightComponent } from './app.availableflightcomponent';
import { Routes, RouterModule } from '@angular/router';
import { BookingDetailComponent } from './app.bookingdetailcomponent';

const frsRoutes:Routes=[
    {path: '', redirectTo: 'home', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'availableflights/:src/:dest/:doj', component: AvailableFlightComponent},
    {path: 'availableflights', component: AvailableFlightComponent},
    {path: 'enterdetails/:flightId', component: BookingDetailComponent}
]

@NgModule({
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule,
        RouterModule.forRoot(frsRoutes)    
    ],
    declarations: [
        AppComponent,
        HomeComponent,
        AvailableFlightComponent,
        BookingDetailComponent
		],
    providers: [ ],
    bootstrap: [AppComponent]
})

export class AppModule { }
