import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TransactionService } from '../../core/services/transaction';

@Component({
  selector: 'app-withdraw',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './withdraw.html',
  styleUrls: ['./withdraw.scss']
})
export class Withdraw {

  form: FormGroup;
  message = '';
  loading = false;

  constructor(
    private fb: FormBuilder,
    private service: TransactionService
  ) {
    this.form = this.fb.group({
      accountNumber: ['',  [
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

    console.log('Submitting WITHDRAW:', val);

    this.service.withdraw({
      accountNumber: val.accountNumber,
      amount: val.amount
    }).subscribe({
      next: res => {
        this.message = res;
        this.form.get('amount')?.reset();
        this.loading = false;
      },
     error: err => {
        if (err.status === 404) {
          this.message = 'Account number does not exist';
        } else {
          this.message = 'Error: ' + err.message;
        }
        this.loading = false;
      }

    });
  }
}
