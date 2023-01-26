import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeeShirtDetailComponent } from './tee-shirt-detail.component';

describe('TeeShirtDetailComponent', () => {
  let component: TeeShirtDetailComponent;
  let fixture: ComponentFixture<TeeShirtDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeeShirtDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeeShirtDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
