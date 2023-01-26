import { Injectable } from "@angular/core";
import { categorie } from "../categories";

@Injectable({
    providedIn: 'root'
})
export class dashboardService {

    public getDashboard(): categorie[] {
        return [
            {id:29, name:"Jean", image:"assets/jeans.jpg", description: "Achetez vos jeans 100% coton"},
            {id:30, name:"Manteau", image:"assets/manteau.jpg", description: "Soyey prêts pour l'hiver avec nos manteaux vapeurs"},
            {id:31, name:"Tee-Shirt", image:"assets/teeshirt.webp", description: "Découvrez nos tee-shirt ecologique pour sauver la planète"},
            {id:32, name:"Accessoires", image:"assets/montre.jfif", description: "Venez découvir nos accessoires"}
        ]
    }
}