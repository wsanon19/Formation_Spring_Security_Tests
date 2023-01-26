import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {
  form: any ={
    email: null,
    password: null,
  }
  constructor(

  ) { }

  ngOnInit(): void {
  }
  onSubmit(): void{
    console.log(this.form)
    
  }
 
}
