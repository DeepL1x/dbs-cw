import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ReadyItem} from "../readyItem/ready-item";
import {WaitingItem} from "./waiting-item";

@Injectable({
  providedIn: 'root'
})
export class WaitingItemService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getWaitingItems(): Observable<WaitingItem[]>{
    return this.http.get<WaitingItem[]>(`${this.apiServerUrl}/waitingItem/all`);
  }

  public getWaitingItemDescByPrice(): Observable<WaitingItem[]>{
    return this.http.get<WaitingItem[]>(`${this.apiServerUrl}/waitingItem/all/descByPrice`);
  }

  public addWaitingItem(waitingItem: WaitingItem): Observable<WaitingItem>{
    return this.http.post<WaitingItem>(`${this.apiServerUrl}/waitingItem/add`, waitingItem);
  }
  public updateWaitingItem(waitingItem: WaitingItem): Observable<WaitingItem>{
    return this.http.put<WaitingItem>(`${this.apiServerUrl}/waitingItem/update`, waitingItem);
  }
  public deleteWaitingItem(id: string): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/waitingItem/delete/${id}`);
  }
}
