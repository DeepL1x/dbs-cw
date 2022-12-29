import {InvoiceItem} from "./invoice-item";

export interface Invoice{
  id: string;
  items: InvoiceItem[];
  approved: boolean;
  totalSum: number;
}
