import { Routes } from '@angular/router';
import { HomeComponent } from './home/home';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    children: [
      {
        path: '',
        loadComponent: () =>
          import('./home/dashboard/dashboard').then(m => m.DashboardComponent)
      },
      {
        path: 'customers',
        loadChildren: () =>
          import('./customer/customer.routes').then(m => m.CUSTOMER_ROUTES)
      },
      {
        path: 'accounts',
        loadChildren: () =>
          import('./account/account.routes').then(m => m.ACCOUNT_ROUTES)
      },
      {
        path: 'transactions',
        loadChildren: () =>
          import('./transaction/transaction.routes').then(m => m.TRANSACTION_ROUTES)
      },
      { path: '**', redirectTo: '' }
    ]
  }
];
