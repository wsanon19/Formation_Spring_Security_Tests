package com.security.forma_security.Repos;

import com.security.forma_security.Model.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeRepos extends JpaRepository<LigneCommande, Long> {

    LigneCommande findLigneCommandeById(Long Id);
}
