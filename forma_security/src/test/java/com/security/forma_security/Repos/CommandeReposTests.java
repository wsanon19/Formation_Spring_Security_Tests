package com.security.forma_security.Repos;

import com.security.forma_security.Model.Commande;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
public class CommandeReposTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommandeRepos commandeRepos;

    @Test
    public void whenFindCommandeByUser_IdthenReturnCommande() {
        Commande test = new Commande();
        test.setId(200L);
        test.setCreation(new Date());

        entityManager.persist(test);
        entityManager.flush();

        Commande Found = commandeRepos.findCommandeById(test.getId());

        assertThat(Found.getId()).isEqualTo(test.getId());

    }






}
