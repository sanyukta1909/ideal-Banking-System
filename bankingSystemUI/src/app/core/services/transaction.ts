import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaction } from '../models/transaction';

@Injectable({ providedIn: 'root' })
export class TransactionService {

  private baseUrl = 'http://localhost:8080/api/transactions';

  constructor(private http: HttpClient) {}

  deposit(data: any): Observable<string> {
    return this.http.post(`${this.baseUrl}/deposit`, data, { responseType: 'text' });
  }

  withdraw(data: any): Observable<string> {
    return this.http.post(`${this.baseUrl}/withdraw`, data, { responseType: 'text' });
  }

  transfer(data: any): Observable<string> {
    return this.http.post(`${this.baseUrl}/transfer`, data, { responseType: 'text' });
  }
history(accountNumber: string) {
  console.log('Calling URL:', `${this.baseUrl}/${accountNumber}`);
  return this.http.get<Transaction[]>(`${this.baseUrl}/${accountNumber}`);
}
}



