import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CountryService } from '../services/CountryService';
import { Country } from '../domain/Country';

@Component({
  selector: 'app-tour-modifier',
  templateUrl: './tour-modifier.component.html',
  styleUrls: ['./tour-modifier.component.css']
})
export class TourModifierComponent implements OnInit {

  tourForm: FormGroup;
  countries: Country[];
  selectedCountry: Country;

  constructor(
    private formBuilder: FormBuilder,
    private countryService: CountryService
  ) { }

  ngOnInit() {
    this.buildForm();
    this.countryService.getAll().subscribe(
      (countries: Country[]) => this.countries = countries 
    );
  }

  private buildForm() {
    this.tourForm = this.formBuilder.group({
      date: [
        '',
        [
          Validators.required
        ]
      ],
      description: [
        '',
        [
          Validators.required
        ]
      ],
      cost: [
        '',
        [
          Validators.required
        ]
      ],
    });
  }

}
