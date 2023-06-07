import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPrefPageComponent } from './user-pref-page.component';

describe('UserPrefPageComponent', () => {
  let component: UserPrefPageComponent;
  let fixture: ComponentFixture<UserPrefPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserPrefPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserPrefPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
