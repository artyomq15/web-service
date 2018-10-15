import { Injectable } from "@angular/core";
import { TourService } from "./TourService";
import { Observable, throwError } from "rxjs";
import { Tour } from "../domain/Tour";
import { map, catchError } from "rxjs/operators";
import { HttpClient } from "@angular/common/http";

@Injectable()
export class TourRestService implements TourService {

    private URI = 'http://localhost:8080/api/tours';

    constructor(private http: HttpClient) { }

    public getAll(): Observable<Tour[]> {
        return this.http
            .get(this.URI)
            .pipe(
                map((tours: Tour[]) => tours),
                catchError((err) => throwError(err))
            );
    }

    public get(id: number): Observable<Tour> {
        return this.http
            .get(`${this.URI}/${id}`)
            .pipe(
                map((tour: Tour) => tour),
                catchError((err) => throwError(err))
            );
    }

    public getByCountry(id: number): Observable<Tour[]> {
        return this.getAll();
    }

    public add(tour: Tour): Observable<Tour> {
        return this.http
            .post(this.URI, tour)
            .pipe(
                map((tour: Tour) => tour),
                catchError((err) => throwError(err))
            );
    }

    public update(tour: Tour): Observable<Tour> {
        return this.http
            .put(this.URI, tour)
            .pipe(
                map((tour: Tour) => tour),
                catchError((err) => throwError(err))
            );
    }

    public remove(id: number): Observable<Tour> {
        return this.http
            .delete(`${this.URI}/${id}`)
            .pipe(
                map((tour: Tour) => tour),
                catchError((err) => throwError(err))
            );
    }
}