import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TransactionService } from '../../core/services/transaction';

@Component({
  selector: 'app-deposit',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './deposit.html'
})
export class Deposit {

  form: FormGroup;
  message = '';
  loading = false;

  constructor(private fb: FormBuilder, private service: TransactionService) {
    this.form = this.fb.group({
      accountNumber: ['', [
      Validators.required,
    ]],
      amount: ['', [Validators.required, Validators.min(1)]]
    });
  }

  submit() {
    if (this.form.invalid || this.loading) {
      return;
    }

    this.loading = true;
    const val = this.form.value;

    console.log('Submitting DEPOSIT:', val);

    this.service.deposit({
      accountNumber: val.accountNumber,
      amount: val.amount
    }).subscribe({
      next: res => {
        this.message = res;
        this.form.get('amount')?.reset();
        this.loading = false;
      },
      error: err => {
        this.message = 'Error: ' + err.message;
        this.loading = false;
      }
    });
  }
}

