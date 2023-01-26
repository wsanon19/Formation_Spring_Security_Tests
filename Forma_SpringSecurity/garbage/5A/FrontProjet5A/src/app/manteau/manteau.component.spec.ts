import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManteauComponent } from './manteau.component';

describe('ManteauComponent', () => {
  let component: ManteauComponent;
  let fixture: ComponentFixture<ManteauComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManteauComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManteauComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
