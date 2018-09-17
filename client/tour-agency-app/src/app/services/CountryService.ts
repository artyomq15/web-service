import { Observable } from "rxjs";
import { Country } from "../domain/Country";
import { Injectable } from "@angular/core";

@Injectable()
export abstract class CountryService {
    abstract findAll(): Observable<Country[]>;
    abstract findOne(id: number): Observable<Country>;
    abstract save(country: Country): Observable<Country>;
    abstract update(country: Country): Observable<Country>;
    abstract delete(id: number): Observable<Country>;
}