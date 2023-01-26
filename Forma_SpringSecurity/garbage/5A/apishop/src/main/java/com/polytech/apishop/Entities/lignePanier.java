package com.polytech.apishop.Entities;

import javax.persistence.*;

@Entity
public class lignePanier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_lignePanier;
    private int quantite;
    private float prix;
    @OneToOne
    private article article;

    @ManyToOne
    @JoinColumn(name = "id_panier", referencedColumnName = "id_panier")
    private panier panier;

    public lignePanier(){

    }

    public lignePanier(Integer id_lignePanier, int quantite, float prix, article article, panier panier){
        this.id_lignePanier = id_lignePanier;
        this.quantite = quantite;
        this.prix = prix;
        this.article = article;
        this.panier = panier;
    }

    public Integer getId_lignePanier() {
        return this.id_lignePanier;
    }

    public void setId_lignePanier(Integer id_lignePanier) {
        this.id_lignePanier = id_lignePanier;
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

    public panier getPanier() {
        return this.panier;
    }

    public void setPanier(panier panier) {
        this.panier = panier;
    }
}
