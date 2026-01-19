import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AccountService } from '../../core/services/account';

@Component({
  selector: 'app-create-account',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './create-account.html',
  styleUrls: ['./create-account.scss']
})
export class CreateAccount implements OnInit {

  accountForm!: FormGroup;
  message = '';
  accountNumber: string | null = null;

  constructor(
    private fb: FormBuilder,
    private accountService: AccountService
  ) {}

  ngOnInit(): void {
    this.accountForm = this.fb.group({
      customerId: ['', Validators.required],
      accountType: ['', Validators.required],
      balance: ['',[ Validators.required,Validators.min(1)]]
    });
  }

  submit() {
    if (this.accountForm.invalid) return;
     console.log('Request payload:', this.accountForm.value);

    this.accountService.createAccount(this.accountForm.value).subscribe({
      next: (res: any) => {
        this.message = res.message;
        this.accountNumber = res.accountNumber;
        this.accountForm.reset();
      },
       error: err => {
        if (err.status === 404) {
          this.message = 'Customer does not exist';
        } else {
          this.message = 'Create account failed';
        }
      }
    });
  }
}
