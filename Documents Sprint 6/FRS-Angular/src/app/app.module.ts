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
import { AdminPageComponent } from 'src/app/app.adminpagecomponent';
import { ExcelUploadComponent } from './app.exceluploadcomponent';
import { SearchComponent } from './app.searchcomponent';
import { ModifyComponent } from './app.modifycomponent';
import { ShowComponent } from './app.showcomponent';
import { AddComponent } from "./app.addcomponent";
import { AddScheduleFlightComponent } from './app.addscheduleflightcomponent';
import { ShowScheduleFlightComponent } from './app.showscheduleflightcomponent';
import { SearchScheduleFlightComponent } from './app.searchscheduleflightcomponent';
import { NotAuthorizedComponent } from './app.notauthorizedcomponent';
import { ConfirmationPopoverModule } from 'angular-confirmation-popover';
import { ErrorComponent } from './app.errorcomponent';

const frsRoutes:Routes=[
    {path: '', redirectTo: 'home', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'booking/availableflights/:src/:dest/:doj', component: AvailableFlightComponent},
    {path: 'booking/availableflights', component: AvailableFlightComponent, canActivate:[AuthGuardService]},
    {path: 'booking/enterdetails/:flightId', component: BookingDetailComponent},
    {path: 'booking/confirmation/:bookingId', component: BookingConfirmationComponent},
    {path: 'showbookings', component: ShowBookingsComponent},
    {path: 'login', component: LoginComponent},
    {path: 'logout', component: LogoutComponent},
    {path: 'userpanel', component: UserPanelComponent},
    {path: 'flight/add', component: AddComponent},
    {path: 'flight/view', component: ShowComponent},
    {path: 'flight/search', component: SearchComponent},
    {path: 'flight/modify/:flightNumber', component: ModifyComponent},
    {path: 'adminpanel', component: AdminPageComponent},
    {path: 'flight/upload', component: ExcelUploadComponent},
    {path:'scheduleFlight/add',component:AddScheduleFlightComponent},
    {path:'scheduleFlight/show',component:ShowScheduleFlightComponent},
    {path:'scheduleFlight/search',component:SearchScheduleFlightComponent},
    {path: 'noauth', component: NotAuthorizedComponent},
    {path: '**', component: ErrorComponent}
]

@NgModule({
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule,
        RouterModule.forRoot(frsRoutes),
        NgxPaginationModule,
        ConfirmationPopoverModule.forRoot({
            confirmButtonType: 'danger'
        })
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
        FooterComponent,
        AddComponent,
        ShowComponent,
        SearchComponent,
        ModifyComponent,
        AdminPageComponent,
        ExcelUploadComponent,
        AddScheduleFlightComponent,
        ShowScheduleFlightComponent,
        SearchScheduleFlightComponent,
        NotAuthorizedComponent,
        ErrorComponent
		],
    providers: [{
        provide: HTTP_INTERCEPTORS,
        useClass: HttpErrorInterceptor,
        multi: true
    }],
    bootstrap: [AppComponent]
})

export class AppModule { }
