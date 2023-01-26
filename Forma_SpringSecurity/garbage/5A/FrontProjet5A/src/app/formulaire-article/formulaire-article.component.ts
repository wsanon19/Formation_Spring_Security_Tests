import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { NgForm } from '@angular/forms';
import { article } from './article';

@Component({
  selector: 'app-formulaire-article',
  templateUrl: './formulaire-article.component.html',
  styleUrls: ['./formulaire-article.component.css'],
  //encapsulation: ViewEncapsulation.None
})
export class FormulaireArticleComponent implements OnInit {

  public article: article = new article();

  constructor() { }

  ngOnInit(): void {
  }

  public Ajouter(registerForm:NgForm) {
    console.log(registerForm.form);
    console.log('valeurs: ', JSON.stringify(registerForm.value));
    console.log("hello")
  }

}
 