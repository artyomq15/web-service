import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TourModifierComponent } from './tour-modifier.component';

describe('TourModifierComponent', () => {
  let component: TourModifierComponent;
  let fixture: ComponentFixture<TourModifierComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TourModifierComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TourModifierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
