import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Article} from "./Article";
import * as http from "http";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getArticles(): Observable<Article[]>{
    return this.http.get<Article[]>(`${this.apiServerUrl}/article/all`);
  }
  public addArticle(article: Article): Observable<Article>{
    return this.http.post<Article>(`${this.apiServerUrl}/article/add`, article);
  }
  public updateArticle(article: Article): Observable<Article>{
    return this.http.put<Article>(`${this.apiServerUrl}/article/update`, article);
  }
  public deleteArticle(articleId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/article/delete/${articleId}`)
  }
}
