import { Component, OnInit } from '@angular/core';
import { article } from '../article';
import { manteauService } from './manteau.service';

@Component({
  selector: 'app-manteau',
  templateUrl: './manteau.component.html',
  styleUrls: ['./manteau.component.css']
})
export class ManteauComponent implements OnInit {

  public errMsg: string | undefined;

  constructor(private manteauService: manteauService) { }

  listeManteau: article[] = [];

  ngOnInit(): void {
    this.manteauService.getManteau().subscribe({
      next: listeManteau => {
        this.listeManteau = listeManteau;
      },

      error: err => this.errMsg = err
    });
  }

}
