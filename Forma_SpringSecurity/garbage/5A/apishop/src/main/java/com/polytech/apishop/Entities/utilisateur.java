package com.polytech.apishop.Entities;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable=false)
    private String login;
    @Column(nullable=false)
    private String mdp;
    private String nom;
    private String prenom;
    private String email;
    private Date date_creation_compte;

    @OneToMany(mappedBy = "utilisateur")
    private List<role> role;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "id_panier", referencedColumnName = "id_panier")
    private panier panier;

    @OneToMany(mappedBy = "utilisateur")
    private List<commande> commande;

    @OneToMany(mappedBy = "utilisateur")
    private List<commentaire> commentaire;

    public utilisateur(){

    }

    public utilisateur(String login, String mdp, String nom, String prenom, String email, List<role> role, Date date_creation_compte, panier panier, List<commande> commande, List<commentaire> commentaire){
        this.login = login;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
        this.date_creation_compte = date_creation_compte;
        this.panier = panier;
        this.commande = commande;
        this.commentaire = commentaire;
    }






    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return this.mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<role> isRole() {
        return this.role;
    }

    public void setRole(List<role> role) {
        this.role = role;
    }

    public Date getDate_creation_compte() {
        return this.date_creation_compte;
    }

    public void setDate_creation_compte(Date date_creation_compte) {
        this.date_creation_compte = date_creation_compte;
    }

    public panier getPanier() {
        return this.panier;
    }

    public void setPanier(panier panier) {
        this.panier = panier;
    }

    public List<commande> getCommande() {
        return this.commande;
    }

    public void setCommande(List<commande> commande) {
        this.commande = commande;
    }

    
    public List<commentaire> getCommentaire() {
        return this.commentaire;
    }

    public void setCommentaire(List<commentaire> commentaire) {
        this.commentaire = commentaire;
    }

}
