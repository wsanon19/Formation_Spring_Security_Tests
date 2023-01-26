import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-start-rating',
  templateUrl: './start-rating.component.html',
  styleUrls: ['./start-rating.component.css']
})
export class StartRatingComponent implements OnInit {

  public starWidth: number = 0;
  
  @Input()
  public note: number = 2;

  constructor() { }

  ngOnInit() {
    this.starWidth = this.note * 125 / 5;
    console.log("nombre d'étoiles", this.starWidth)
  }

}
