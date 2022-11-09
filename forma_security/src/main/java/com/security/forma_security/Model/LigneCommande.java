package com.security.forma_security.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.AUTO;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private int nmbr;

    @ManyToOne
    private Article article;
}
