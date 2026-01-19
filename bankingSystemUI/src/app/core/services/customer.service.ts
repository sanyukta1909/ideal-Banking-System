import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  checkMobile(value: any) {
    throw new Error('Method not implemented.');
  }

  private apiUrl = 'http://localhost:8080/api/customers';

constructor(private http: HttpClient) {}

  createCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(this.apiUrl, customer);
  }


}
