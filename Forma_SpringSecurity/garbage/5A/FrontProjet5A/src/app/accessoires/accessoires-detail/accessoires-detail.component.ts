import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { article } from 'src/app/article';
import { accessoiresService } from '../accessoires.service';


@Component({
  selector: 'app-accessoires-detail',
  templateUrl: './accessoires-detail.component.html',
  styleUrls: ['./accessoires-detail.component.css']
})
export class AccessoiresDetailComponent implements OnInit {

  public accessoire: article = <article>{};

  constructor(
    private route: ActivatedRoute,
    private listeAccessoire: accessoiresService
  ) { }

  ngOnInit(): void {
    const id: number = +this.route.snapshot.paramMap.get('id')!; //+ pour convertir un string en un nombre snapshot recupère la valeur initiale
    
    this.listeAccessoire.getAccessoires().subscribe((liste: article[]) => {
      this.accessoire = liste.find(accessoire => accessoire.id_article == id)!;
      console.log('hotel', this.accessoire);
    })
  }

}
