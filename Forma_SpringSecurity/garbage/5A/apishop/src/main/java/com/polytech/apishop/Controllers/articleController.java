package com.polytech.apishop.Controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polytech.apishop.Entities.article;
import com.polytech.apishop.Repos.articleRepository;
import com.polytech.apishop.Services.articleService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/article")
public class articleController {
    
    @Autowired
    private articleService articleServ;

    @Autowired
    private articleRepository articleResp;

    @GetMapping("/liste")
    public List<article> voirListeArticles() {
        return articleServ.voirListeArticles();
    }
    @GetMapping(path = "/description/{id}")
    public String voirDescriptionArticle(@PathVariable(value = "id") Integer id_article) {
        return articleServ.voirDescriptionArticle(id_article);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimerArticle(@PathVariable(value = "id") Integer id_article) {
        try {
            articleResp.deleteById(id_article);
        } catch (Exception e){}
    }

    // /article/categorie/liste

    // /article/categorie/liste

    @PutMapping("/modifier/{id}")
    public ResponseEntity<article> modifierArticle(@PathVariable(value = "id") Integer id_article,@RequestBody article modif){
        Optional<article> articleModifier = articleResp.findById(id_article);
        if (articleModifier.isPresent()) {
            return new ResponseEntity<>(articleResp.save(articleServ.modifierArticle(articleModifier, modif)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
