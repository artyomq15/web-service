import { Component, OnInit, Input, Inject, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CountryService } from '../services/CountryService';
import { Country } from '../domain/Country';
import { Tour } from '../domain/Tour';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

export enum ModificationType {
  ADD, UPDATE
}

@Component({
  selector: 'app-tour-modifier',
  templateUrl: './tour-modifier.component.html',
  styleUrls: ['./tour-modifier.component.css']
})
export class TourModifierComponent implements OnInit {

  tourForm: FormGroup;
  countries: Country[];
  selectedCountry: number;

  @Output() updated: EventEmitter<Tour> = new EventEmitter<Tour>();
  @Output() added: EventEmitter<Tour> = new EventEmitter<Tour>();

  constructor(
    public dialogRef: MatDialogRef<TourModifierComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { type: ModificationType, tour: Tour },
    private formBuilder: FormBuilder,
    private countryService: CountryService
  ) { }

  ngOnInit() {
    this.countryService.getAll().subscribe(
      (countries: Country[]) => this.countries = countries 
    );
    this.buildForm();
  }

  onSubmit() {
    if (this.tourForm.valid) {

      const tour = new Tour(
        1,
        this.tourForm.controls.date.value,
        this.tourForm.controls.description.value,
        this.tourForm.controls.cost.value,
        this.tourForm.controls.countryId.value
      );

      console.log(tour);

      if (this.data.type === ModificationType.ADD) {
        this.added.emit(tour);
      } else {
        tour.id = this.data.tour.id;
        this.updated.emit(tour);
      }

      this.close();
    }
  }

  close() {
    this.dialogRef.close();
  }

  private buildForm() {
    this.tourForm = this.formBuilder.group({
      date: [
        this.data.type === ModificationType.UPDATE ? this.data.tour.date : '',
        [
          Validators.required
        ]
      ],
      description: [
        this.data.type === ModificationType.UPDATE ? this.data.tour.description : '',
        [
          Validators.required
        ]
      ],
      cost: [
        this.data.type === ModificationType.UPDATE ? this.data.tour.cost : 0,
        [
          Validators.required
        ]
      ],
      countryId: [
        this.data.type === ModificationType.UPDATE ? this.data.tour.countryId : 0,
        [
          Validators.required,
          Validators.min(1)
        ]
      ],
    });
  }

}
