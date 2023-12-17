import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TwoWayInputComponent } from './two-way-input.component';

describe('TwoWayInputComponent', () => {
  let component: TwoWayInputComponent;
  let fixture: ComponentFixture<TwoWayInputComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TwoWayInputComponent]
    });
    fixture = TestBed.createComponent(TwoWayInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
