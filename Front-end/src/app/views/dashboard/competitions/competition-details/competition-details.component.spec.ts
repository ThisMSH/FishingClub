import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetitionDetailsComponent } from './competition-details.component';

describe('CompetitionDetailsComponent', () => {
  let component: CompetitionDetailsComponent;
  let fixture: ComponentFixture<CompetitionDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CompetitionDetailsComponent]
    });
    fixture = TestBed.createComponent(CompetitionDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
