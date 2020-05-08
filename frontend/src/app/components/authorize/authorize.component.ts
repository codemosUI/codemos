import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-authorize',
  templateUrl: './authorize.component.html',
  styleUrls: ['./authorize.component.css']
})
export class AuthorizeComponent implements OnInit {

  constructor(private _router: Router) { }

  ngOnInit(): void {
  }

  onAuthorize() {
    this._router.navigate(['/home']);
  }
}
