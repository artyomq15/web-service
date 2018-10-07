import { Observable } from "rxjs";
import { Country } from "../domain/Country";
import { Injectable } from "@angular/core";

@Injectable()
export abstract class CountryService {
    abstract getAll(): Observable<Country[]>;
    abstract get(id: number): Observable<Country>;
    abstract add(country: Country): Observable<Country>;
    abstract update(country: Country): Observable<Country>;
    abstract remove(id: number): Observable<Country>;
}