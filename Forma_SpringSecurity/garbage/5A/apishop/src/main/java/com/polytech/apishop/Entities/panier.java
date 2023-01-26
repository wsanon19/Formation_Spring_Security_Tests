package com.polytech.apishop.Entities;

import javax.persistence.*;

@Entity
public class panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_panier;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private utilisateur utilisateur;

    private float prix_total;

    public panier(){

    }

    public panier(float prix_total, utilisateur utilisateur){
        this.prix_total = prix_total;
        this.utilisateur = utilisateur;
    }

    public Integer getId_panier() {
        return this.id_panier;
    }

    public void setId_panier(Integer id_panier) {
        this.id_panier = id_panier;
    }

    public float getPrix_total() {
        return this.prix_total;
    }

    public void setPrix_total(float prix_total) {
        this.prix_total = prix_total;
    }
    
    public utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
