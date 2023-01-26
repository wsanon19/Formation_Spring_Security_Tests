import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { article } from "../article";
import { Observable } from "rxjs";
import { catchError, tap } from "rxjs/operators";
import { HttpErrorResponse } from "@angular/common/http";
import { throwError } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root'
})

export class jeanService {

    // private readonly JEAN_API_URL = '/assets/api/jean.json';
    private readonly JEAN_API_URL = environment.host;

    constructor(private http: HttpClient){}

    public getJean(): Observable<article[]> {

        // return this.http.get<article[]>(this.JEAN_API_URL).pipe(
        //     tap(jeanHomme => console.log('jeans: ', jeanHomme)),
        //     catchError(this.handleError)
        // );

       
        return this.http.get<article[]>(`${this.JEAN_API_URL}/article/liste`).pipe(
          tap(jeanHomme => console.log('jeans: ', jeanHomme)),
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