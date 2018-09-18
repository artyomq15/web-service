import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { Tour } from "../domain/Tour";

@Injectable()
export abstract class CountryService {
    abstract findAll(): Observable<Tour[]>;
    abstract findByCountry(id: number): Observable<Tour[]>;
    abstract findOne(id: number): Observable<Tour>;
    abstract save(tour: Tour): Observable<Tour>;
    abstract update(tour: Tour): Observable<Tour>;
    abstract delete(id: number): Observable<Tour>;
}