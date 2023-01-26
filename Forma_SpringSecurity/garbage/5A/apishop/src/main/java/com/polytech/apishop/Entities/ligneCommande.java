package com.polytech.apishop.Entities;
import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class ligneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ligneCommande;
    private int quantite;
    private float prix;
    @OneToOne
    private article article;
    @ManyToOne
    @JoinColumn(name = "id_commande", referencedColumnName = "id_commande")
    private commande commande;

    public ligneCommande(){

    }

    public ligneCommande(Integer id_ligneCommande, int quantite, float prix, article article,commande commande){
        this.id_ligneCommande = id_ligneCommande;
        this.quantite = quantite;
        this.prix = prix;
        this.article = article;
        this.commande = commande;
    }

    public Integer getId_ligneCommande() {
        return this.id_ligneCommande;
    }

    public void setId_ligneCommande(Integer id_ligneCommande) {
        this.id_ligneCommande = id_ligneCommande;
    }

    public int getQuantite() {
        return this.quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix() {
        return this.prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public article getArticle() {
        return this.article;
    }

    public void setArticle(article article) {
        this.article = article;
    }
    public commande getCommande() {
        return this.commande;
    }

    public void setCommande(commande commande) {
        this.commande = commande;
    }

}
