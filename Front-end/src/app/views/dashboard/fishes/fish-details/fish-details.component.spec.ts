import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FishDetailsComponent } from './fish-details.component';

describe('FishDetailsComponent', () => {
  let component: FishDetailsComponent;
  let fixture: ComponentFixture<FishDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FishDetailsComponent]
    });
    fixture = TestBed.createComponent(FishDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
