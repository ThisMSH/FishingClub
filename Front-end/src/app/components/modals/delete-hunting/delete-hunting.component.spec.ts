import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteHuntingComponent } from './delete-hunting.component';

describe('DeleteHuntingComponent', () => {
  let component: DeleteHuntingComponent;
  let fixture: ComponentFixture<DeleteHuntingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DeleteHuntingComponent]
    });
    fixture = TestBed.createComponent(DeleteHuntingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
