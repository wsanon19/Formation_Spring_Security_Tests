import { Component, OnInit } from '@angular/core';
import { article } from '../article';
import { accessoiresService } from './accessoires.service';

@Component({
  selector: 'app-accessoires',
  templateUrl: './accessoires.component.html',
  styleUrls: ['./accessoires.component.css']
})
export class AccessoiresComponent implements OnInit {

  public errMsg: string | undefined;

  constructor(private articleService: accessoiresService) { }

  listeAccessoires: article[] = [];

  ngOnInit(): void {
    this.articleService.getAccessoires().subscribe({
      next: listeAccessoires => {
        this.listeAccessoires = listeAccessoires;
      },

      error: err => this.errMsg = err
    });
  }

 

}
