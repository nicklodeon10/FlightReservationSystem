import {HttpEvent, 
        HttpInterceptor, 
        HttpHandler, 
        HttpRequest, 
        HttpResponse, 
        HttpErrorResponse
    } from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {retry, catchError} from 'rxjs/operators';
//import { MatSnackBar } from '@angular/material/snack-bar';

export class HttpErrorInterceptor implements HttpInterceptor{

    //constructor(public snackBar: MatSnackBar){}

    intercept(request:HttpRequest<any>, next:HttpHandler):Observable<HttpEvent<any>>{
        return next.handle(request)
        .pipe(
            retry(1),
            catchError((error: HttpErrorResponse)=>{
                let errorMessage='';
                if(error.error instanceof ErrorEvent){
                    errorMessage=`Error: ${error.error.message}`;
                }else{
                    errorMessage=`${error.error}`;
                    if(error.status==0){
                        errorMessage='Could not connect to Web Service';
                    }
                }
                window.alert(errorMessage);
                //this.snackBar.open(errorMessage, 'Close');
                return throwError(errorMessage);
            })
        )
    }

}