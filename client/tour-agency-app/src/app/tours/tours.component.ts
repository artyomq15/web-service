import { Component, OnInit } from '@angular/core';
import { Tour } from '../domain/Tour';
import { TourService } from '../services/TourService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tours',
  templateUrl: './tours.component.html',
  styleUrls: ['./tours.component.css']
})
export class ToursComponent implements OnInit {

  tours: Tour[];

  constructor(
    private tourService: TourService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getAll();
  }

  public getAll() {
    this.tourService.getAll().subscribe(
      (tours: Tour[]) => {
        this.tours = tours;
      },
      (err) => this.router.navigate(['/not_found'])
    );
  }

  public add(tour: Tour) {
    this.tourService.add(tour).subscribe(
      () => this.getAll(),
      (err) => this.router.navigate(['/not_found'])
    );
  }

  public update(tour: Tour) {
    this.tourService.update(tour).subscribe(
      () => this.getAll(),
      (err) => this.router.navigate(['/not_found'])
    );
  }

  public delete(id: number) {
    this.tourService.remove(id).subscribe(
      () => this.getAll(),
      (err) => this.router.navigate(['/not_found'])
    );
  }

  

}
