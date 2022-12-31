import { Component } from '@angular/core';
import {Article} from "./enities/article/Article";
import {HttpErrorResponse} from "@angular/common/http";
import {ArticleService} from "./enities/article/article.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'dbs-cw-ui';
  public articles: Article[] | undefined;

  constructor(private articleService: ArticleService) {}

  ngOnInit(){
    this.getArticles();
  }

  public getArticles(): void{
    this.articleService.getArticles().subscribe(
      (response: Article[])=>{
        this.articles = response;
        console.log(this.articles);},
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
  }
}
