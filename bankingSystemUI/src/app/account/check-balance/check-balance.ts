import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AccountService } from '../../core/services/account';

@Component({
  selector: 'app-check-balance',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './check-balance.html',
  styleUrls: ['./check-balance.scss']
})
export class CheckBalance implements OnInit {

  balanceForm!: FormGroup;
  balanceMessage = '';
  balance: number | null = null;
  message: any;
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private accountService: AccountService
  ) {}

  ngOnInit(): void {
    this.balanceForm = this.fb.group({
      accountNumber: ['',
        Validators.required,
      ]
    });
  }

  submit() {
    if (this.balanceForm.invalid) return;
    this.message = '';
    this.errorMessage = '';
    this.balance = null;
    const accountNumber = this.balanceForm.value.accountNumber;
    console.log('Checking balance for account:', accountNumber);

    this.accountService.getBalance(accountNumber).subscribe({
      next: (res: number) => {
        this.balance = res;
        this.balanceForm.reset();
      },
         error: (err) => {
      if (err.status === 404) {
        this.errorMessage = 'Account not found';
      } else if (err.status === 500) {
        this.errorMessage = 'Server error. Please try again later.';
      } else {
        this.errorMessage = 'Something went wrong';
      }
    }
  });
  }
}
