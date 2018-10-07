import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { Tour } from "../domain/Tour";

@Injectable()
export abstract class TourService {
    abstract getAll(): Observable<Tour[]>;
    abstract getByCountry(id: number): Observable<Tour[]>;
    abstract get(id: number): Observable<Tour>;
    abstract add(tour: Tour): Observable<Tour>;
    abstract update(tour: Tour): Observable<Tour>;
    abstract remove(id: number): Observable<Tour>;
}