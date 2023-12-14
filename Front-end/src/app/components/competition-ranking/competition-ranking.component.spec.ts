import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetitionRankingComponent } from './competition-ranking.component';

describe('CompetitionRankingComponent', () => {
  let component: CompetitionRankingComponent;
  let fixture: ComponentFixture<CompetitionRankingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CompetitionRankingComponent]
    });
    fixture = TestBed.createComponent(CompetitionRankingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
