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
import { LoginComponent } from './app.logincomponent';
import { LogoutComponent } from './app.logoutcomponent';
import { AuthGuardService } from '../app/_service/app.authguardservice';
import { UserPanelComponent } from './app.userpanelcomponent';
import { HeaderComponent } from './app.headercomponent';
import { FooterComponent } from './app.footercomponent';

const frsRoutes:Routes=[
    {path: '', redirectTo: 'home', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'availableflights/:src/:dest/:doj', component: AvailableFlightComponent},
    {path: 'availableflights', component: AvailableFlightComponent, canActivate:[AuthGuardService]},
    {path: 'enterdetails/:flightId', component: BookingDetailComponent},
    {path: 'bookingconfirmation', component: BookingConfirmationComponent},
    {path: 'showbookings', component: ShowBookingsComponent},
    {path: 'login', component: LoginComponent},
    {path: 'logout', component: LogoutComponent},
    {path: 'userpanel', component: UserPanelComponent}
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
        ShowBookingsComponent,
        LoginComponent,
        LogoutComponent,
        UserPanelComponent,
        HeaderComponent,
        FooterComponent
		],
    providers: [{
        provide: HTTP_INTERCEPTORS,
        useClass: HttpErrorInterceptor,
        multi: true
    }],
    bootstrap: [AppComponent]
})

export class AppModule { }
