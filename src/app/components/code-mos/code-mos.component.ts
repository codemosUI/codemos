import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-code-mos',
  templateUrl: './code-mos.component.html',
  styleUrls: ['./code-mos.component.css']
})
export class CodeMosComponent implements OnInit {
  centered = false;
  disabled = false;
  unbounded = false;

  radius: number;
  color: string;
  constructor() { }

  ngOnInit(): void {
  }

}
