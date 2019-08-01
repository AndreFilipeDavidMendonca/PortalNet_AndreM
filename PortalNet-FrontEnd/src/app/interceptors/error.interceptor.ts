import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
import { AlertService } from '../alert.service';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  message= '';
  constructor(private authenticationService: AuthenticationService, private router: Router, private alertService: AlertService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(catchError(err => {
      let error = err.error;

      if (err.status === 400) {
        // this.message = "O registo não foi processado! Certifique se os seus dados estão correctos!"
        this.authenticationService.logout();
        this.router.navigate(['/home']);
        // error.message = err.error.message.slice(1, err.error.message.length - 1).split(', ');
      }
      else if (err.status === 401) {
        // auto logout if 401 response returned from api
        this.authenticationService.logout();
        this.router.navigate(['/home']);
      }
      // auto logout if 403 response returned from api
      else if (err.status === 403) {
        this.authenticationService.logout();
        this.router.navigate(['/home']);
      }

      else if (err.status === 404) {
        return throwError(error.message);
      }

      else if (err.status === 302) {
        this.authenticationService.logout();
        this.router.navigate(['/home']);
      }

      else if (err.status === 206) {
        this.authenticationService.logout();
        this.router.navigate(['/home']);
      }
    
      else if (err.statusText === 'Unknown Error' && err.name === 'HttpErrorResponse') {
        error.message = "Não foi possível conectar ao servidor!";
        return throwError(error.message);
      }

      return throwError(error);

    }));
  }
}