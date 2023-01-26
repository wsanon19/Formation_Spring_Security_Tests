import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { catchError, tap } from "rxjs/operators";
import { Observable, throwError } from "rxjs";
import { article } from "../article";

@Injectable({
    providedIn: 'root'
})
export class accessoiresService {

    private readonly ACCESSOIRES_API_URL = '/assets/api/accessoires.json';

    constructor(private http: HttpClient){}

    public getAccessoires(): Observable<article[]> {
        return this.http.get<article[]>(this.ACCESSOIRES_API_URL).pipe(
            tap(accessoire => console.log('jeans: ', accessoire)),
            catchError(this.handleError)
        );
    }

    private handleError(error: HttpErrorResponse) {
        if (error.status === 0) {
          // A client-side or network error occurred. Handle it accordingly.
          console.error('An error occurred:', error.error);
        } else {
          // The backend returned an unsuccessful response code.
          // The response body may contain clues as to what went wrong.
          console.error(
            `Backend returned code ${error.status}, body was: `, error.error);
        }
        // Return an observable with a user-facing error message.
        return throwError(() => new Error('Something bad happened; please try again later.'));
      }

}


