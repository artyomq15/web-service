import { Component, OnInit } from '@angular/core';
import { Tour } from '../domain/Tour';
import { TourService } from '../services/TourService';

@Component({
  selector: 'app-tour-modifier',
  templateUrl: './tour-modifier.component.html',
  styleUrls: ['./tour-modifier.component.css']
})
export class TourModifierComponent implements OnInit {

  saveDate: Date = null;
  saveDescription: string = '';
  saveCost: number = 0;
  saveCountryId: number = 0;
  savedTour: Tour;

  updateId: number = 1;
  updateDate: Date = null;
  updateDescription: string = '';
  updateCost: number = 0;
  updateCountryId: number = 0;
  updatedTour: Tour;

  getId: number = 1;
  tour: Tour;

  deleteId: number = 1;
  deletedTour: Tour;

  toursByCountry: Tour[];
  countryId: number = 1;

  tours: Tour[];

  constructor(private tourService: TourService) { }

  ngOnInit() {
  }

  public findAllTours() {
    this.tourService.findAll().subscribe(
      res => this.tours = res
    );
  }

  public save() {
    this.saveTour(new Tour(
      1,
      this.saveDate,
      this.saveDescription,
      this.saveCost,
      this.saveCountryId
    ));
  }

  private saveTour(tour: Tour) {
    this.tourService.save(tour).subscribe(
      (res: Tour) => {
        this.savedTour = res;
      },
      (err:Error) => {
        this.savedTour = new Tour(0, new Date(), 'not a tour', 0, 0);
      }
    );
  }

  public update() {
    this.updateTour(new Tour(
      this.updateId,
      this.updateDate,
      this.updateDescription,
      this.updateCost,
      this.updateCountryId
    ));
  }

  private updateTour(tour: Tour) {
    this.tourService.update(tour).subscribe(
      (res: Tour) => {
        this.updatedTour = res;
      },
      (err:Error) => {
        this.updatedTour = new Tour(0, new Date(), 'not a tour', 0, 0);
      }
    );
  }

  public delete() {
    this.deleteTour(this.deleteId);
  }

  private deleteTour(id: number) {
    this.tourService.delete(id).subscribe(
      (res: Tour) => {
        this.deletedTour = res;
      },
      (err:Error) => {
        this.deletedTour = new Tour(0, new Date(), 'not a tour', 0, 0);
      }
    );
  }

  public get() {
    this.getTour(this.getId);
  }

  private getTour(id: number) {
    this.tourService.findOne(id).subscribe(
      (res: Tour) => {
        this.tour = res;
        
        console.log(this.tour);
      },
      (err:Error) => {
        this.tour = new Tour(0, new Date(), 'not a tour', 0, 0);
      }
    );
  }


  public getByCountry() {
    this.getTourByCountry(this.countryId);
  }

  private getTourByCountry(id: number) {
    this.tourService.findByCountry(id).subscribe(
      (res: Tour[]) => {
        this.toursByCountry = res;
      }
    );
  }

}
