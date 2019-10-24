import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FileUploader} from 'ng2-file-upload';
import {HttpClient} from '@angular/common/http';
import { Router } from '@angular/router';
 
//Author: Devang
//Description: Component for adding passenger details
//Created On: 21/10/2019

@Component({
  selector: 'excelupload',
  templateUrl: '/_pages/app.excelupload.html',
  
})
export class ExcelUploadComponent  {

    title = 'Upload Flight Data';

    constructor (private router:Router,private httpService: HttpClient) {  }
  
    myFiles:string [] = [];
    sMsg:string = '';
  
    ngOnInit () { 
      if(sessionStorage.getItem('role')==='user'){
        this.router.navigate(['noauth']);
      }
     }
    getFileDetails (e) {
      //console.log (e.target.files);
      for (var i = 0; i < e.target.files.length; i++) { 
        this.myFiles.push(e.target.files[i]);
      }
    }
  
    uploadFiles () {
      const frmData = new FormData();
      
      for (var i = 0; i < this.myFiles.length; i++) { 
        frmData.append("file", this.myFiles[i]);
      }
      
      this.httpService.post('http://localhost:9088/flight/upload', frmData).subscribe(
        data => {
          // SHOW A MESSAGE RECEIVED FROM THE WEB API.
          this.sMsg = data as string;
          console.log (this.sMsg);
        }
        // ,
        // (err: HttpErrorResponse) => {
        //   console.log (err.message);    // Show error, if any.
        // }
      );
    }
  }
