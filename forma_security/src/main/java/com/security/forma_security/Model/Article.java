package com.security.forma_security.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String nom;
    private String description;
    private double prix;


    public Article(String nom, String description, Double prix) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }
}
