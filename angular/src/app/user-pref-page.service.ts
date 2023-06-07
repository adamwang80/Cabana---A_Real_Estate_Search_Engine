import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs/internal/Observable";
import { catchError, map, tap } from "rxjs/operators";
import { of } from "rxjs/internal/observable/of";

import { RentalData } from "./user-pref-page/rental-data.model";
import { UserPrefData } from "./user-pref-page/user-pref-data.model";

@Injectable({
  providedIn: "root",
})
export class UserPrefPageService {
  private rentalDataUrl =
    "http://localhost:8080/api/googlemaps/fetchAvailableRentals";
  httpOptions = {
    headers: new HttpHeaders({ "Content-Type": "application/json" }),
  };
  // dummyRentalData: RentalData = {
  //   rentalRequest: {
  //     destination: "1350 Marietta Blvd NW, Atlanta, GA 30318, USA",

  //     commuteType: "car",

  //     bedroomType: "1bed",

  //     latitude: 33.7898658,

  //     longitude: -84.4298215,
  //   },

  //   rentalResponse: [
  //     {
  //       apartmentName: "Icon Buckhead",

  //       latitude: 33.8487652,

  //       longitude: -84.3671019,

  //       address: "3372 Peachtree Rd NE, Atlanta, GA 30326, USA",

  //       unit: "1bed",

  //       price: 2386,

  //       distance: "15.5 km",

  //       duration: "19 mins",

  //       imageLink: "TBD",
  //     },

  //     {
  //       apartmentName: "Willowest in Collier Hills",

  //       latitude: 33.8093416,

  //       longitude: -84.415868,

  //       address: "914 Collier Rd NW, Atlanta, GA 30318, USA",

  //       unit: "1bed",

  //       price: 1575,

  //       distance: "5.1 km",

  //       duration: "9 mins",

  //       imageLink: "TBD",
  //     },

  //     {
  //       apartmentName: "Kingsboro Luxury Apartments",

  //       latitude: 33.8493703,

  //       longitude: -84.35794530000001,

  //       address: "3443 Lakeside Dr NE, Atlanta, GA 30326, USA",

  //       unit: "1bed",

  //       price: 1638,

  //       distance: "15.6 km",

  //       duration: "20 mins",

  //       imageLink: "TBD",
  //     },

  //     {
  //       apartmentName: "Cyan On Peachtree",

  //       latitude: 33.849634,

  //       longitude: -84.36610999999999,

  //       address: "3380 Peachtree Rd NE UNIT 1513, Atlanta, GA 30326, USA",

  //       unit: "1bed",

  //       price: 1928,

  //       distance: "15.5 km",

  //       duration: "19 mins",

  //       imageLink: "TBD",
  //     },
  //   ],
  // };

  private searchResults;

  constructor(private http: HttpClient) {}

  private handleError<T>(operation = "operation", result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  getRentalData(userPreferencesData: UserPrefData): Observable<RentalData> {
    console.log("Get rental data called");
    // return of(this.dummyRentalData);
    // http://localhost:8080/api/googlemaps/fetchAvailableRentals

    return this.http
      .post<RentalData>(
        this.rentalDataUrl,
        userPreferencesData,
        this.httpOptions
      )
      .pipe(
        tap((rentalDataResponse: RentalData) =>
          {
            console.log("Rental data fetched successfully: ", rentalDataResponse);
            this.searchResults =  rentalDataResponse;
          }
        ),
        catchError(this.handleError<RentalData>("getRentalData"))
      );
  }

  getSearchResults()
  {
    return this.searchResults;
  }
}
