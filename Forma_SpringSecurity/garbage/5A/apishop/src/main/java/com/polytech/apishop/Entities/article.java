package com.polytech.apishop.Entities;

import lombok.AllArgsConstructor;

import java.util.List;

import javax.persistence.*;

@Entity
@AllArgsConstructor
public class article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_article;
    private String name;
    private String description;
    private float prix;
    private String taille;
    @Column(nullable = false)
    private int quantite_stock;
    private String image;
    
    @ManyToOne
    @JoinColumn(name = "id_categories", referencedColumnName = "id_categories")
    private categories categories;

    @ManyToOne
    @JoinColumn(name = "id_commande", referencedColumnName = "id_commande")
    private commande commande;

    @ManyToOne
    @JoinColumn(name = "id_panier", referencedColumnName = "id_panier")
    private panier panier;

    @OneToMany(mappedBy = "article")
    private List<commentaire> commentaire;

    public article() {

    }

    public article(String name, String description, float prix, String taille, int quantite_stock, String image, categories categories, commande commande, panier panier, List<commentaire> commentaire) {
        this.name = name;
        this.description = description;
        this.prix = prix;
        this.taille = taille;
        this.quantite_stock = quantite_stock;
        this.image = image;
        this.categories = categories;
        this.commande = commande;
        this.panier = panier;
        this.commentaire = commentaire;
    }

    public Integer getId_article() {
        return this.id_article;
    }

    public void setId_article(Integer id_article) {
        this.id_article = id_article;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return this.prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getTaille() {
        return this.taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public int getQuantite_stock() {
        return this.quantite_stock;
    }

    public void setQuantite_stock(int quantite_stock) {
        this.quantite_stock = quantite_stock;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public categories getCategories() {
        return this.categories;
    }

    public void setCategories(categories categories) {
        this.categories = categories;
    }

    public panier getPanier() {
        return this.panier;
    }

    public void setPanier(panier panier) {
        this.panier = panier;
    }
    
    public commande getCommande() {
        return this.commande;
    }

    public void setCommande(commande commande) {
        this.commande = commande;
    }

    public List<commentaire> getCommentaire() {
        return this.commentaire;
    }

    public void setCommentaire(List<commentaire> commentaire) {
        this.commentaire = commentaire;
    }
}
