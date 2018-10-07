import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Country } from '../domain/Country';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-update-country-dialog',
  templateUrl: './update-country-dialog.component.html',
  styleUrls: ['./update-country-dialog.component.css']
})
export class UpdateCountryDialogComponent implements OnInit {

  updateForm: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<UpdateCountryDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {name: string},
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.buildForm();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSubmit() {
    if (this.updateForm.valid){
      return this.updateForm.controls.name.value;
    }
  }

  private buildForm() {
    this.updateForm = this.formBuilder.group({
      name: [
        this.data.name,
        [
          Validators.required
        ]
      ]
    });
  }

}
