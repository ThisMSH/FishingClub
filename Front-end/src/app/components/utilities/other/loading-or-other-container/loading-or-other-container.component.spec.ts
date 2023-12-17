import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadingOrOtherContainerComponent } from './loading-or-other-container.component';

describe('LoadingOrOtherContainerComponent', () => {
  let component: LoadingOrOtherContainerComponent;
  let fixture: ComponentFixture<LoadingOrOtherContainerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LoadingOrOtherContainerComponent]
    });
    fixture = TestBed.createComponent(LoadingOrOtherContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
