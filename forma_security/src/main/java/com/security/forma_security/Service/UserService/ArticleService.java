package com.security.forma_security.Service.UserService;

import com.security.forma_security.Model.Article;
import com.security.forma_security.Repos.ArticleRepos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepos articleRepos;

    public ArticleService(ArticleRepos articleRepos) {
        this.articleRepos = articleRepos;
    }

    public Article getArticle(Long Id) {
        return articleRepos.findArticleById(Id);
    }

    public List<Article> getArticles() {
        return articleRepos.findAll();
    }

    public Article create(String nom, String description, Double prix) {
        Article artcl = new Article(nom,description,prix);
        articleRepos.save(artcl);
        return artcl;
    }






}
