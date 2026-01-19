      import { Component, OnInit } from '@angular/core';
      import { CommonModule } from '@angular/common';
      import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule, RouterOutlet } from "@angular/router";

      @Component({
        selector: 'app-account',
        standalone: true,
        imports: [CommonModule ,RouterModule,RouterOutlet],
        templateUrl: './account.html',
        styleUrls: ['./account.scss']
      })
export class AccountComponent {
  constructor(public router: Router) {}
}

