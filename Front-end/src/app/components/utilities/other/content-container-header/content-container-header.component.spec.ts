import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContentContainerHeaderComponent } from './content-container-header.component';

describe('ContentContainerHeaderComponent', () => {
  let component: ContentContainerHeaderComponent;
  let fixture: ComponentFixture<ContentContainerHeaderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ContentContainerHeaderComponent]
    });
    fixture = TestBed.createComponent(ContentContainerHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
