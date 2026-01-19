import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { TransactionService } from '../../core/services/transaction';

@Component({
  selector: 'app-transfer',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './transfer.html',
  styleUrls: ['./transfer.scss']
})
export class Transfer {

  form: FormGroup;
  message = '';
  loading = false;

  constructor(
    private fb: FormBuilder,
    private service: TransactionService
  ) {
    this.form = this.fb.group({
      fromAccount: ['', [
        Validators.required,
      ]
      ],
      toAccount: ['', [
        Validators.required,
      ] 
      ],
      amount: ['', [Validators.required, Validators.min(1)]]
    },
    {
        validators: this.accountsNotSameValidator
    }
    
  );
  }
  
    // ✅ Group-level validator
  accountsNotSameValidator(control: AbstractControl): ValidationErrors | null {
    const from = control.get('fromAccount')?.value;
    const to = control.get('toAccount')?.value;

    if (from && to && from === to) {
      return { sameAccount: true };
    }
    return null;
  }

  submit() {
    if (this.form.invalid || this.loading) {
      return;
    }

    this.loading = true;
    const val = this.form.value;

    console.log('Submitting TRANSFER:', val);

    this.service.transfer({
      fromAccount: val.fromAccount,
      toAccount: val.toAccount,
      amount: val.amount
    }).subscribe({
      next: res => {
        this.message = res;
        this.form.get('amount')?.reset();
        this.loading = false;
      },
     error: err => {
    if (err.status === 404) {
            this.message = 'One or both accounts do not exist';
          } else if (err.status === 400) {
            this.message = 'Insufficient balance';
          } else {
            this.message = 'Transfer failed';
          }
          this.loading = false;
        }               
    });
  }
}


