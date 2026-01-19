import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./shared/navbar/navbar";
import { CreateCustomer } from "./customer/create-customer/create-customer";
import { AccountComponent } from './account/account/account';
import { TransactionComponent } from './transaction/transaction/transaction';
import { DashboardComponent } from './home/dashboard/dashboard';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('bankingSystemUI');
}
