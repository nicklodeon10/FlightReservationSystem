import {Component} from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app',
    templateUrl: 'app.component.html'
})

export class AppComponent  {
    constructor( private router:Router){}  

    addScheduleFlight(){

    this.router.navigate(['scheduleFlight/add']);

}
}