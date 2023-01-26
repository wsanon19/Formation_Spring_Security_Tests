import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeeShirtComponent } from './tee-shirt.component';

describe('TeeShirtComponent', () => {
  let component: TeeShirtComponent;
  let fixture: ComponentFixture<TeeShirtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeeShirtComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeeShirtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
