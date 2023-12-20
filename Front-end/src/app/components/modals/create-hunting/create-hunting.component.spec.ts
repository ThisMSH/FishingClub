import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateHuntingComponent } from './create-hunting.component';

describe('CreateHuntingComponent', () => {
  let component: CreateHuntingComponent;
  let fixture: ComponentFixture<CreateHuntingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateHuntingComponent]
    });
    fixture = TestBed.createComponent(CreateHuntingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
