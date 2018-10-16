import { Component, OnInit } from '@angular/core';
import { Tour } from '../domain/Tour';
import { TourService } from '../services/TourService';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { TourModifierComponent, ModificationType } from '../tour-modifier/tour-modifier.component';

@Component({
  selector: 'app-tours',
  templateUrl: './tours.component.html',
  styleUrls: ['./tours.component.css']
})
export class ToursComponent implements OnInit {

  tours: Tour[];

  constructor(
    private tourService: TourService,
    public dialog: MatDialog,
    private router: Router
  ) { }

  ngOnInit() {
    this.getAll();
  }

  openUpdateDialog(tour: Tour): void {
    const update = this.dialog.open(TourModifierComponent, {
      data: { 
        type: ModificationType.UPDATE,
        tour: tour
      }
    });

    update.componentInstance.updated.subscribe(
      (tour: Tour) => this.update(tour)
    );
    
  }

  openAddDialog(): void {
    const add = this.dialog.open(TourModifierComponent, {
      data: { 
        type: ModificationType.ADD,
        tour: null
      }
    });
    add.componentInstance.added.subscribe(
      (tour: Tour) => this.add(tour)
    );
  }

  public getAll() {
    this.tourService.getAll().subscribe(
      (tours: Tour[]) => {
        console.log(tours);
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
