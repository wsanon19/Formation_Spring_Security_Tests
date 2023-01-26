import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManteauDetailComponent } from './manteau-detail.component';

describe('ManteauDetailComponent', () => {
  let component: ManteauDetailComponent;
  let fixture: ComponentFixture<ManteauDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManteauDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManteauDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
