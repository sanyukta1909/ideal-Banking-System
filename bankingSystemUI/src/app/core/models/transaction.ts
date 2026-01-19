export interface Transaction {
  transactionId: number;
  accountId: number;
  transactionType: string;
  amount: number;
  balance: number;
  referenceAccount: string | null;
  status: string;
  transactionDate: string;
  remarks: string;
}
