import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JeanComponent } from './jean/jean.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { ConnexionComponent } from './connexion/connexion.component';

const routes: Routes = [
  {path : "connexion", component : ConnexionComponent},
  {path : "inscription", component : InscriptionComponent} ,
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
