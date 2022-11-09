package com.security.forma_security.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Commande {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private Date creation;
    private boolean validated;

    @ManyToOne
    private AppUser user;

    @OneToMany
    private List<LigneCommande> lignes;

    private double Total;

}
