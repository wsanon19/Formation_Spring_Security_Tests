package com.polytech.apishop.Entities;

import javax.persistence.*;

@Entity
public class role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Integer id_role;
    private String nom;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private utilisateur utilisateur;

    public role(){
        
    }

    public role(String nom,utilisateur utilisateur){
        this.nom = nom;
        this.utilisateur = utilisateur;
    }


    public utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Integer getId_role() {
        return this.id_role;
    }

    public void setId_role(Integer id_role) {
        this.id_role = id_role;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
