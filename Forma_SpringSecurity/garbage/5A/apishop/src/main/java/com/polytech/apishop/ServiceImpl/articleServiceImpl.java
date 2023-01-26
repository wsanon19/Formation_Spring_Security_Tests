package com.polytech.apishop.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polytech.apishop.Entities.article;
import com.polytech.apishop.Repos.articleRepository;
import com.polytech.apishop.Services.articleService;

@Service
public class articleServiceImpl implements articleService {
    
    @Autowired
    private articleRepository articleRep;

    public List<article> voirListeArticles(){
        List<article> listeArticle = articleRep.findAll();
        return listeArticle;
    }

    public String voirDescriptionArticle(Integer id_article){
        String description = articleRep.findDescription(id_article);
        return description;
    }

    public void supprimerArticle(Integer id_article){
        articleRep.deleteById(id_article);
    }

    public void addArticle(String name, String description, float  prix, String taille, int stock, String img ){

        article _article = new article(name,description,prix,taille,stock,img, null,null,null,null);

        articleRep.save(_article);

    }

    public article modifierArticle(Optional<article> articleModifier, article modif){
        article _articleModifier = articleModifier.get();
        
        _articleModifier.setName(modif.getName());
        _articleModifier.setDescription(modif.getDescription());
        _articleModifier.setPrix(modif.getPrix());
        _articleModifier.setTaille(modif.getTaille());
        _articleModifier.setQuantite_stock(modif.getQuantite_stock());
        _articleModifier.setImage(modif.getImage());
        _articleModifier.setName(modif.getName());

        return modif;
    }
}
