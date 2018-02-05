import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';

import { GarageComponent } from './garage.component';

describe('GarageComponent', () => {
  let component: GarageComponent;
  let fixture: ComponentFixture<GarageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GarageComponent ],
      imports: [ FormsModule, RouterTestingModule ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GarageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
