import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import{FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http'; 
import { AddScheduleFlightComponent } from './app.addscheduleflightcomponent';
import { ShowScheduleFlightComponent } from './app.showscheduleflightcomponent';
//import { ModifyScheduleFlightComponent } from './app.modifyscheduleflightcomponent';

import { SearchScheduleFlightComponent } from './app.searchscheduleflightcomponent';
import {Routes,RouterModule}  from '@angular/router'; 

const myroute:Routes=[
  
    {path:'scheduleFlight/add',component:AddScheduleFlightComponent},
    {path:'scheduleFlight/show',component:ShowScheduleFlightComponent},
    {path:'scheduleFlight/search',component:SearchScheduleFlightComponent}
    //{path:'scheduleFlight/modify',component:ModifyScheduleFlightComponent}
]

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        RouterModule.forRoot(myroute)
        
    ],
    declarations: [
        AppComponent,
        AddScheduleFlightComponent,
        ShowScheduleFlightComponent,
        SearchScheduleFlightComponent
        
       
        
		],
    providers: [ ],
    bootstrap: [AppComponent]
})

export class AppModule { }