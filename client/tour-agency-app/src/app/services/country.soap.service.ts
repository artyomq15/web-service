import { Injectable } from '@angular/core';
import { Observable, of, timer, throwError } from 'rxjs';
import { map, catchError, delay } from 'rxjs/operators';
import { Country } from '../domain/Country';
import { CountryService } from './CountryService';
import { NgxSoapService, Client, ISoapMethodResponse } from 'ngx-soap';

@Injectable()
export class CountrySoapService implements CountryService {

    client: Client;
    loading = true;

    constructor(private soap: NgxSoapService) {
        this.createClient();
    }

    private createClient(): void{
        this.soap.createClient('assets/countries.wsdl')
            .then(client => {
                this.client = client;
            })
            .catch(err => throwError(err));
    }
    
    public findAll(): Observable<Country[]> {
        return this.client
                .call('getCountries', {})
                .pipe(
                    map((res: ISoapMethodResponse) => res.result.countries),
                    catchError(err => throwError(err))
                );
    }

    public findOne(id: number): Observable<Country> {
        return this.client
                .call('getCountry', {id})
                .pipe(
                    map((res: ISoapMethodResponse) => res.result.country),
                    catchError(err => throwError(err))
                );
    }
    public save(country: Country): Observable<Country> {
        const body = {
            name: country.name
        }
        return this.client
                .call('saveCountry', body)
                .pipe(
                    map((res: ISoapMethodResponse) => res.result.country),
                    catchError(err => throwError(err))
                );
    }
    public update(country: Country): Observable<Country> {
        const body = {
            id: country.id,
            name: country.name
        }
        return this.client
                .call('updateCountry', body)
                .pipe(
                    map((res: ISoapMethodResponse) => res.result.country),
                    catchError(err => throwError(err))
                );
    }
    public delete(id: number): Observable<Country> {
        return this.client
                .call('deleteCountry', {id})
                .pipe(
                    map((res: ISoapMethodResponse) => res.result.country),
                    catchError(err => throwError(err))
                );
    }
}