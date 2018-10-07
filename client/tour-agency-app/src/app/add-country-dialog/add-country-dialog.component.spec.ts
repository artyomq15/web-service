import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCountryDialogComponent } from './add-country-dialog.component';

describe('AddCountryDialogComponent', () => {
  let component: AddCountryDialogComponent;
  let fixture: ComponentFixture<AddCountryDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCountryDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCountryDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
