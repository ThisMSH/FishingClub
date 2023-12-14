import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetitionInfoComponent } from './competition-info.component';

describe('CompetitionInfoComponent', () => {
  let component: CompetitionInfoComponent;
  let fixture: ComponentFixture<CompetitionInfoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CompetitionInfoComponent]
    });
    fixture = TestBed.createComponent(CompetitionInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
