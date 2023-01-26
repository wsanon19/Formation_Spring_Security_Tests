import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { article } from 'src/app/article';
import { jeanService } from '../jean.service';

@Component({
  selector: 'app-jean-detail',
  templateUrl: './jean-detail.component.html',
  styleUrls: ['./jean-detail.component.css']
})
export class JeanDetailComponent implements OnInit {

  public jean: article = <article>{};

  constructor(
    private route: ActivatedRoute,
    private listeJean: jeanService
  ) { }

  ngOnInit(): void {
    const id: number = +this.route.snapshot.paramMap.get('id')!; //+ pour convertir un string en un nombre snapshot recupère la valeur initiale
    
    this.listeJean.getJean().subscribe((liste: article[]) => {
      this.jean = liste.find(jean => jean.id_article == id)!;
      console.log('note', this.jean.note);
    })
  }

}
