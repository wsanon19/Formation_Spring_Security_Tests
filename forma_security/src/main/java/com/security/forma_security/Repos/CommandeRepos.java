package com.security.forma_security.Repos;

import com.security.forma_security.Model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommandeRepos extends JpaRepository<Commande, Long> {

    Commande findCommandeById(Long Id);

    List<Commande> findAll();




    List<Commande> findCommandesByUserId(Long Id);


}
