import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { article } from '../article';
import { EnteteComponent } from '../entete/entete.component';
import { allArticlesService } from './all-articles.service';

@Component({
  selector: 'app-all-articles',
  templateUrl: './all-articles.component.html',
  styleUrls: ['./all-articles.component.css']
})
export class AllArticlesComponent implements OnInit {

  public article: article = <article>{};

  public entete?: EnteteComponent;

  constructor(
    private route: ActivatedRoute,
    private listeArticles: allArticlesService
  ) { }

  ngOnInit(): void {
    const id: number = +this.route.snapshot.paramMap.get('id')!; //+ pour convertir un string en un nombre snapshot recupère la valeur initiale
    
    this.listeArticles.getArticles().subscribe((liste: article[]) => {
      this.article = liste.find(jean => jean.id_article == id)!;
      console.log('note', this.article.note);
    })
  }

}
