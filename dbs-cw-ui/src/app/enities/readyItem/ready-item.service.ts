import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ReadyItem} from "./ready-item";

@Injectable({
  providedIn: 'root'
})
export class ReadyItemService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getReadyItem(): Observable<ReadyItem[]>{
    return this.http.get<ReadyItem[]>(`${this.apiServerUrl}/readyItem/all`);
  }
  public getReadyItemDescByPrice(): Observable<ReadyItem[]>{
    return this.http.get<ReadyItem[]>(`${this.apiServerUrl}/readyItem/all/descByPrice`);
  }
  public addReadyItem(readyItem: ReadyItem): Observable<ReadyItem>{
    return this.http.post<ReadyItem>(`${this.apiServerUrl}/readyItem/add`, readyItem);
  }
  public updateReadyItem(readyItem: ReadyItem): Observable<ReadyItem>{
    return this.http.put<ReadyItem>(`${this.apiServerUrl}/readyItem/update`, readyItem);
  }
  public deleteReadyItem(id: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/readyItem/delete/${id}`);
  }
}
