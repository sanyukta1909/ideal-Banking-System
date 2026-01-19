import { Routes } from '@angular/router';
import { AccountComponent } from './account/account';

export const ACCOUNT_ROUTES: Routes = [
  {
    path: '',
    component: AccountComponent,
  children: [
      {
        path: '',
        redirectTo: 'create',
        pathMatch: 'full'
      },
      {
        path: 'create',
        loadComponent: () =>
          import('./create-account/create-account')
            .then(m => m.CreateAccount)
      },
      {
        path: 'balance',
        loadComponent: () =>
          import('./check-balance/check-balance')
            .then(m => m.CheckBalance)
      }
    ]
  }
];

