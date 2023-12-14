import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetitionMemberComponent } from './competition-member.component';

describe('CompetitionMemberComponent', () => {
  let component: CompetitionMemberComponent;
  let fixture: ComponentFixture<CompetitionMemberComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CompetitionMemberComponent]
    });
    fixture = TestBed.createComponent(CompetitionMemberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
