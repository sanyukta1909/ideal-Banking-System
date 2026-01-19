import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckBalance } from './check-balance';

describe('CheckBalance', () => {
  let component: CheckBalance;
  let fixture: ComponentFixture<CheckBalance>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CheckBalance]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CheckBalance);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
