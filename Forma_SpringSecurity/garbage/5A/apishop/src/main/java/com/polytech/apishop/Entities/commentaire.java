package com.polytech.apishop.Entities;
import javax.persistence.*;

@Entity
public class commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_commande;

    private String commentaire;
    private int note;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_article", referencedColumnName = "id_article")
    private article article;

    public commentaire(){
        
    }

    public commentaire(String commentaire,int note, utilisateur utilisateur, article article){
        this.commentaire = commentaire;
        this.note = note;
        this.utilisateur = utilisateur;
        this.article = article;
    }

    public Integer getId_commande() {
        return this.id_commande;
    }

    public void setId_commande(Integer id_commande) {
        this.id_commande = id_commande;
    }

    public String getCommentaire() {
        return this.commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getNote() {
        return this.note;
    }

    public void setNote(int note) {
        this.note = note;
    }
    
    public utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    public article getArticle() {
        return this.article;
    }

    public void setArticle(article article) {
        this.article = article;
    }

}
