import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Order} from "./order";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getOrders(): Observable<Order[]>{
    return this.http.get<Order[]>(`${this.apiServerUrl}/order/all`);
  }
  public addOrder(order: Order): Observable<Order>{
    return this.http.post<Order>(`${this.apiServerUrl}/order/create`, order);
  }
  public getOrderById(id: number): Observable<Order>{
    return this.http.get<Order>(`${this.apiServerUrl}/order/getById/${id}`);
  }
  // public getOrders(): Observable<Order[]>{
  //   return this.http.get<Order[]>(`${this.apiServerUrl}/order/addItem`)
  // }
}
