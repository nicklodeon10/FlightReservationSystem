import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from "@angular/forms";
import { AppComponent }  from './app.component';
import { HomeComponent } from './app.home';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AvailableFlightComponent } from './app.availableflightcomponent';
import { Routes, RouterModule } from '@angular/router';
import { BookingDetailComponent } from './app.bookingdetailcomponent';
import { BookingConfirmationComponent } from './app.bookingconfirmationcomponent';
import { ShowBookingsComponent } from './app.showbookingscomponent';
import { HttpErrorInterceptor } from './http-error.interceptor';
import {NgxPaginationModule} from 'ngx-pagination';

const frsRoutes:Routes=[
    {path: '', redirectTo: 'home', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'availableflights/:src/:dest/:doj', component: AvailableFlightComponent},
    {path: 'availableflights', component: AvailableFlightComponent},
    {path: 'enterdetails/:flightId', component: BookingDetailComponent},
    {path: 'bookingconfirmation', component: BookingConfirmationComponent},
    {path: 'showbookings', component: ShowBookingsComponent}
]

@NgModule({
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule,
        RouterModule.forRoot(frsRoutes),
        NgxPaginationModule    
    ],
    declarations: [
        AppComponent,
        HomeComponent,
        AvailableFlightComponent,
        BookingDetailComponent,
        BookingConfirmationComponent,
        ShowBookingsComponent
		],
    providers: [{
        provide: HTTP_INTERCEPTORS,
        useClass: HttpErrorInterceptor,
        multi: true
    }],
    bootstrap: [AppComponent]
})

export class AppModule { }
