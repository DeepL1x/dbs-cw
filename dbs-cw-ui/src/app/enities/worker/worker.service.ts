import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Worker} from "./worker";

@Injectable({
  providedIn: 'root'
})
export class WorkerService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getWorkerStatus(worker: Worker): Observable<Worker>{
    return this.http.post<Worker>(`${this.apiServerUrl}/authorization`, worker);
  }

}
