import { Component, OnInit, Inject, Output, EventEmitter } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Country } from '../domain/Country';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CountryService } from '../services/CountryService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-country-dialog',
  templateUrl: './update-country-dialog.component.html',
  styleUrls: ['./update-country-dialog.component.css']
})
export class UpdateCountryDialogComponent implements OnInit {

  updateForm: FormGroup;

  @Output() updated: EventEmitter<Country> = new EventEmitter<Country>();

  constructor(
    public dialogRef: MatDialogRef<UpdateCountryDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { country: Country },
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.buildForm();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSubmit() {
    if (this.updateForm.valid) {
      this.updated.emit(new Country(this.data.country.id, this.updateForm.controls.name.value));
      this.onNoClick();
    }
  }

  private buildForm() {
    this.updateForm = this.formBuilder.group({
      name: [
        this.data.country.name,
        [
          Validators.required
        ]
      ]
    });
  }

}
