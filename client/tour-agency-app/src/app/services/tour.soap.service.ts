import { Injectable } from "@angular/core";
import { TourService } from "./TourService";
import { NgxSoapService, Client, ISoapMethodResponse } from "ngx-soap";
import { Observable, throwError } from "rxjs";
import { Tour } from "../domain/Tour";
import { map, catchError } from "rxjs/operators";

@Injectable()
export class TourSoapService implements TourService {

    client: Client;

    constructor(private soap: NgxSoapService) {
        this.createClient();
    }

    private createClient(): void{
        this.soap.createClient('assets/tours.wsdl')
            .then(client => {
                this.client = client;
            })
            .catch(err => throwError(err));
    }

    public findAll(): Observable<Tour[]> {
        return this.client
                .call('getTours', {})
                .pipe(
                    map((res: ISoapMethodResponse) => res.result.tours),
                    catchError(err => throwError(err))
                );
    }

    public findByCountry(id: number): Observable<Tour[]> {
        return this.client
            .call('getToursByCountry', {id})
            .pipe(
                map((res: ISoapMethodResponse) => res.result.tours),
                catchError(err => throwError(err))
            );
    }

    public findOne(id: number): Observable<Tour> {
        return this.client
                .call('getTour', {id})
                .pipe(
                    map((res: ISoapMethodResponse) => res.result.tour),
                    catchError(err => throwError(err))
                );
    }

    public save(tour: Tour): Observable<Tour> {
        console.log(tour);
        const body = {
            date: tour.date,
            description: tour.description,
            cost: tour.cost,
            countryId: tour.countryId
        }
        return this.client
                .call('saveTour', body)
                .pipe(
                    map((res: ISoapMethodResponse) => res.result.tour),
                    catchError(err => throwError(err))
                );
    }

    public update(tour: Tour): Observable<Tour> {
        const body = {
            id: tour.id,
            date: tour.date,
            description: tour.description,
            cost: tour.cost,
            countryId: tour.countryId
        }
        return this.client
                .call('updateTour', body)
                .pipe(
                    map((res: ISoapMethodResponse) => res.result.tour),
                    catchError(err => throwError(err))
                );
    }

    public delete(id: number): Observable<Tour> {
        return this.client
            .call('deleteTour', {id})
            .pipe(
                map((res: ISoapMethodResponse) => res.result.tour),
                catchError(err => throwError(err))
            );
    }
}