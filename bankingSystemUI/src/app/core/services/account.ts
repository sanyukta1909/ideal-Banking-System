import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, map, Observable, of } from 'rxjs';
import { AccountResponse } from '../models/account-response';

@Injectable({ providedIn: 'root' })
export class AccountService {

  private baseUrl = 'http://localhost:8080/api/accounts';

  constructor(private http: HttpClient) {}

    createAccount(data: any): Observable<AccountResponse> {
    return this.http.post<AccountResponse>(`${this.baseUrl}`, data);
  }


 getBalance(accountNumber: string): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/${accountNumber}/balance`);
  }

}
