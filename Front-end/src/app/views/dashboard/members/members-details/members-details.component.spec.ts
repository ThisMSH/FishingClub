import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MembersDetailsComponent } from './members-details.component';

describe('MembersDetailsComponent', () => {
  let component: MembersDetailsComponent;
  let fixture: ComponentFixture<MembersDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MembersDetailsComponent]
    });
    fixture = TestBed.createComponent(MembersDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
