package com.polytech.apishop.Entities;

import java.util.List;

import javax.persistence.*;

@Entity
public class categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_categories;

    private String nom;

    private String description;

    @OneToMany(mappedBy = "categories")
    private List<article> article;

    public Integer getId_categories() {
        return this.id_categories;
    }

    public void setId_categories(Integer id_categories) {
        this.id_categories = id_categories;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<article> getArticle() {
        return this.article;
    }

    public void setArticle(List<article> article) {
        this.article = article;
    }

}
