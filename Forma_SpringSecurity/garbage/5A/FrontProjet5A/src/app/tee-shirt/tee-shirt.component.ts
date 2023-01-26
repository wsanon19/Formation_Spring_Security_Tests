import { Component, OnInit } from '@angular/core';
import { article } from '../article';
import { teeshirtService } from './tee-shirt.service';

@Component({
  selector: 'app-tee-shirt',
  templateUrl: './tee-shirt.component.html',
  styleUrls: ['./tee-shirt.component.css']
})
export class TeeShirtComponent implements OnInit {

  public errMsg: string | undefined;

  constructor(private teeShirtService: teeshirtService) { }

  listeTeeShirt: article[] = [];

  ngOnInit(): void {
    this.teeShirtService.getTeeShirt().subscribe({
      next: listeTeeShirt => {
        this.listeTeeShirt = listeTeeShirt;
      },

      error: err => this.errMsg = err
    });
  }

  

}
