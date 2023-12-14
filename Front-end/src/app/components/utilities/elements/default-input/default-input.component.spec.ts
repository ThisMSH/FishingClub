import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefaultInputComponent } from './default-input.component';

describe('DefaultInputComponent', () => {
  let component: DefaultInputComponent;
  let fixture: ComponentFixture<DefaultInputComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DefaultInputComponent]
    });
    fixture = TestBed.createComponent(DefaultInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
