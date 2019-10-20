import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from "@angular/forms";
import { AppComponent }  from './app.component';
import { HomeComponent } from './app.home';
import { HttpClientModule } from '@angular/common/http';
import { AvailableFlightComponent } from './app.availableflightcomponent';

@NgModule({
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule    
    ],
    declarations: [
        AppComponent,
        HomeComponent,
        AvailableFlightComponent
		],
    providers: [ ],
    bootstrap: [AppComponent]
})

export class AppModule { }
