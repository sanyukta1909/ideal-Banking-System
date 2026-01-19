import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Transaction } from '../../core/models/transaction';
import { TransactionService } from '../../core/services/transaction';
import { Deposit } from "../deposit/deposit";
import { Withdraw } from "../withdraw/withdraw";
import { Transfer } from "../transfer/transfer";
import { History } from "../history/history";

@Component({
  selector: 'app-transaction',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, Deposit, Withdraw, Transfer, History],
  templateUrl: './transaction.html',
  styleUrls: ['./transaction.scss']
})
export class TransactionComponent {

  mode: 'deposit' | 'withdraw' | 'transfer' | 'history' = 'deposit';
    setMode(mode: any) {
    this.mode = mode;
  }
}
