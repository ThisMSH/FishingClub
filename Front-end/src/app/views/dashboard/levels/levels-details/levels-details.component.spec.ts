import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LevelsDetailsComponent } from './levels-details.component';

describe('LevelsDetailsComponent', () => {
  let component: LevelsDetailsComponent;
  let fixture: ComponentFixture<LevelsDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LevelsDetailsComponent]
    });
    fixture = TestBed.createComponent(LevelsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
