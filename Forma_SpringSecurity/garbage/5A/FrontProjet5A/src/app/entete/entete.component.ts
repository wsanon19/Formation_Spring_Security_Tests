import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { allArticlesService } from '../all-articles/all-articles.service';
import { article } from '../article';

@Component({
  selector: 'app-entete',
  templateUrl: './entete.component.html',
  styleUrls: ['./entete.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class EnteteComponent implements OnInit {

  public articleFilter = 'article';
  articles: article[] = [];

  filteredArticles: article[] = [];

  public errMsg: string | undefined;

  constructor(
    private listeArticles: allArticlesService
  ) { }

  ngOnInit() {
    this.listeArticles.getArticles().subscribe({
      next: articles => {
        this.articles = articles;
        this.filteredArticles = this.articles;
      },
      error: err => this.errMsg = err
    });
  
  }

  public get articlesFiltered(): string {

    return this.articleFilter;
  }

  public set articlesFiltered(filter:string) {
    
    this.articleFilter = filter;

    this.filteredArticles = this.articlesFiltered ? this.filtered(this.articlesFiltered) : this.articles; // si notre articleFiltered reçoit 
    //une nouvelle valeur alors on filtre les hotels avec pour criter la valeur de l'hoter sauvegardée avec le get  sinon retourne la liste des articles
  }

  private filtered(criteria: string): article[] {
    
    criteria = criteria.toLocaleLowerCase();

    const res = this.articles.filter(
      (article: article) => article.name.toLocaleLowerCase().indexOf(criteria)!=-1
    );

    return res;
  }

}
