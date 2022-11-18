package com.security.forma_security.Repos;

import com.security.forma_security.Model.Article;
import com.security.forma_security.Model.Commande;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ArticleReposTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ArticleRepos articleRepos;

    @Test
    public void when_findarticlebyid_should_retur_article() {
        Article test = new Article();
        test.setId(200L);
        test.setNom("Jeans");

        entityManager.persist(test);
        entityManager.flush();

        Article Found = articleRepos.findArticleById(test.getId());

        assertThat(Found.getId()).isEqualTo(test.getId());
    }
}
