import { Injectable } from '@angular/core';
import { Observable, of, timer, throwError } from 'rxjs';
import { map, catchError, delay } from 'rxjs/operators';
import { Country } from '../domain/Country';
import { CountryService } from './CountryService';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class CountryRestService implements CountryService {

    private URI = 'http://localhost:8080/api/countries';

    constructor(private http: HttpClient) { }
    
    public getAll(): Observable<Country[]> {
        return this.http
            .get(this.URI)
            .pipe(
                map((countries: Country[]) => countries),
                catchError((err) => throwError(err))
            );
    }

    public get(id: number): Observable<Country> {
        return this.http
            .get(`${this.URI}/${id}`)
            .pipe(
                map((country: Country) => country),
                catchError((err) => throwError(err))
            );
    }

    public add(country: Country): Observable<Country> {
        return this.http
            .post(this.URI, country)
            .pipe(
                map((country: Country) => country),
                catchError((err) => throwError(err))
            );
    }

    public update(country: Country): Observable<Country> {
        return this.http
            .put(this.URI, country)
            .pipe(
                map((country: Country) => country),
                catchError((err) => throwError(err))
            );
    }

    public remove(id: number): Observable<Country> {
        return this.http
            .delete(`${this.URI}/${id}`)
            .pipe(
                map((country: Country) => country),
                catchError((err) => throwError(err))
            );
    }
}