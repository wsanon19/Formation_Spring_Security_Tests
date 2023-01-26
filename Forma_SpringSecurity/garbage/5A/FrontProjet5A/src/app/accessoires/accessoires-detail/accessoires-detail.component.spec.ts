import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccessoiresDetailComponent } from './accessoires-detail.component';

describe('AccessoiresDetailComponent', () => {
  let component: AccessoiresDetailComponent;
  let fixture: ComponentFixture<AccessoiresDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccessoiresDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccessoiresDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
