import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CountryModifierComponent } from './country-modifier.component';

describe('CountryModifierComponent', () => {
  let component: CountryModifierComponent;
  let fixture: ComponentFixture<CountryModifierComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CountryModifierComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CountryModifierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
