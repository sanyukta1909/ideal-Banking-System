import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TransactionService } from '../../core/services/transaction';
import { Transaction } from '../../core/models/transaction';

@Component({
  selector: 'app-history',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './history.html',
  styleUrls: ['./history.scss']
})
export class History implements OnInit {
  historyForm!: FormGroup;
  transactions: Transaction[] = [];
  loading = false;
  message = '';

  constructor(
  private fb: FormBuilder,
  private service: TransactionService,
  private cdr: ChangeDetectorRef
) {}


  ngOnInit() {
    // Initialize form
    this.historyForm = this.fb.group({
      accountNumber: ['',  
        Validators.required 
      ]
    });
  }

 getHistory() {
  if (this.historyForm.invalid) return;

  this.loading = true;
  this.transactions = [];
  this.message = '';

  const accountNumber = this.historyForm.value.accountNumber;

  this.service.history(accountNumber).subscribe({
    next: (res: Transaction[]) => {
      console.log('RAW RESPONSE:', res);

      this.transactions = res;
      this.loading = false;

      // 🔥 FORCE UI UPDATE
      this.cdr.detectChanges();

      if (res.length === 0) {
        this.message = 'No transactions found';
      }
    },
    error: err => {
      this.loading = false;
      this.message =
        err.status === 404 ? 'Account does not exist' : 'Error fetching history';

      this.cdr.detectChanges();
    }
  });
}

}
