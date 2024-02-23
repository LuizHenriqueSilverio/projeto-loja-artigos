import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterialToolbarComponent } from './material-toolbar.component';

describe('MaterialToolbarComponent', () => {
  let component: MaterialToolbarComponent;
  let fixture: ComponentFixture<MaterialToolbarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MaterialToolbarComponent]
    });
    fixture = TestBed.createComponent(MaterialToolbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
