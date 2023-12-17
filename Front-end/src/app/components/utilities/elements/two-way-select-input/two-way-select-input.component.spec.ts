import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TwoWaySelectInputComponent } from './two-way-select-input.component';

describe('TwoWaySelectInputComponent', () => {
  let component: TwoWaySelectInputComponent;
  let fixture: ComponentFixture<TwoWaySelectInputComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TwoWaySelectInputComponent]
    });
    fixture = TestBed.createComponent(TwoWaySelectInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
