import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Invoice} from "./invoice";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getInvoices(): Observable<Invoice[]>{
    return this.http.get<Invoice[]>(`${this.apiServerUrl}/invoice/getInvoice`);
  }
  public addInvoice(id: number): Observable<Invoice>{
    return this.http.post<Invoice>(`${this.apiServerUrl}/invoice/createInvoice/${id}`, null);
  }
  public approveInvoice(id: number): Observable<Invoice>{
    return this.http.post<Invoice>(`${this.apiServerUrl}/invoice/approve/${id}`, null);
  }
}
