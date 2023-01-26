import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JeanDetailComponent } from './jean-detail.component';

describe('JeanDetailComponent', () => {
  let component: JeanDetailComponent;
  let fixture: ComponentFixture<JeanDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JeanDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JeanDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
