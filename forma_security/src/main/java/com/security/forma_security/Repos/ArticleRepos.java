package com.security.forma_security.Repos;

import com.security.forma_security.Model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepos extends JpaRepository<Article, Long> {

    Article findArticleById(Long Id);

    List<Article> findArticleByNomContaining(String a); // ast

    List<Article> findAll();

    boolean existsArticleById(Long Id);
}
