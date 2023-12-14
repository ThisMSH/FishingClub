import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetitionMemberDetailsComponent } from './competition-member-details.component';

describe('CompetitionMemberDetailsComponent', () => {
  let component: CompetitionMemberDetailsComponent;
  let fixture: ComponentFixture<CompetitionMemberDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CompetitionMemberDetailsComponent]
    });
    fixture = TestBed.createComponent(CompetitionMemberDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
