import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CodeMosComponent } from './code-mos.component';

describe('CodeMosComponent', () => {
  let component: CodeMosComponent;
  let fixture: ComponentFixture<CodeMosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CodeMosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CodeMosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
