import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { categorie } from '../categories';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver) {}

  listeCategories: categorie[] = [
    {id:25, name:"Jean", image:"assets/jeans.jpg", description: "Achetez vos jeans 100% coton"},
    {id:26, name:"Manteau", image:"assets/manteau.jpg", description: "Soyey prêts pour l'hiver avec nos manteaux vapeurs"},
    {id:27, name:"Tee-Shirt", image:"assets/teeshirt.webp", description: "Découvrez nos tee-shirt ecologique pour sauver la planète"},
    {id:28, name:"Accessoires", image:"assets/montre.jfif", description: "Venez découvir nos accessoires"}
  ];

}
