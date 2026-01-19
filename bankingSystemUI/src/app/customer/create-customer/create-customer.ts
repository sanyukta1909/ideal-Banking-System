import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CustomerService } from '../../core/services/customer.service';
import { Customer } from '../../core/models/customer';
import { catchError, map, of } from 'rxjs';

@Component({
  selector: 'app-create-customer',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './create-customer.html'
})
export class CreateCustomer implements OnInit {

  customerForm: FormGroup;
  customers: Customer[] = [];

  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService
  ) {
    this.customerForm = this.fb.group({
      customerName: [ '',   [Validators.required, Validators.pattern('^[A-Za-z ]+$')] ],
      email: [ '', [Validators.required, Validators.email] ],
      mobile: [ '', [Validators.required, Validators.pattern('^[0-9]{10}$')], ]
    });
  }

  ngOnInit(): void {
    this.submit();
  }

  submit(): void {
    if (this.customerForm.invalid) {
      return;
    }

    const formValue = this.customerForm.value;

    this.customerService.createCustomer(formValue).subscribe({
      next: (newCustomer: Customer) => {
        console.log('Customer created:', newCustomer);

        // Add to table
        this.customers.unshift(newCustomer);

        // Reset form
        this.customerForm.reset();
      },
      error: (err) => console.error('Create customer failed', err)
    });
  }
}

