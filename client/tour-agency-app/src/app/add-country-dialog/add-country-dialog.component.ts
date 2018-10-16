import { Component, OnInit, Inject, Output, EventEmitter } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-add-country-dialog',
  templateUrl: './add-country-dialog.component.html',
  styleUrls: ['./add-country-dialog.component.css']
})
export class AddCountryDialogComponent implements OnInit {

  addForm: FormGroup;

  @Output() added: EventEmitter<string> = new EventEmitter<string>();

  constructor(
    public dialogRef: MatDialogRef<AddCountryDialogComponent>,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.buildForm();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSubmit() {
    if (this.addForm.valid) {
      this.added.emit(this.addForm.controls.name.value);
      this.onNoClick();
    }
  }

  private buildForm() {
    this.addForm = this.formBuilder.group({
      name: [
        '',
        [
          Validators.required
        ]
      ]
    });
  }

}
