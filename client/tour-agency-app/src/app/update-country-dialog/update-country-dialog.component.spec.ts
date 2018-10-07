import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCountryDialogComponent } from './update-country-dialog.component';

describe('UpdateCountryDialogComponent', () => {
  let component: UpdateCountryDialogComponent;
  let fixture: ComponentFixture<UpdateCountryDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateCountryDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCountryDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
