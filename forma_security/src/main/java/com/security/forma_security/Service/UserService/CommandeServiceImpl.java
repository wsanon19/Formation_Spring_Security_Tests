package com.security.forma_security.Service.UserService;

import com.security.forma_security.Model.AppUser;
import com.security.forma_security.Model.Article;
import com.security.forma_security.Model.Commande;
import com.security.forma_security.Model.LigneCommande;
import com.security.forma_security.Repos.ArticleRepos;
import com.security.forma_security.Repos.CommandeRepos;
import com.security.forma_security.Repos.LigneCommandeRepos;
import com.security.forma_security.Repos.UserRepos;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommandeServiceImpl implements CommandeService{

    private final CommandeRepos commandeRepos;
    private final LigneCommandeRepos ligneCommandeRepos;
    private final ArticleRepos articleRepos;
    private final UserRepos userRepos;


    public CommandeServiceImpl(CommandeRepos commandeRepos, LigneCommandeRepos ligneCommandeRepos, ArticleRepos articleRepos, UserRepos userRepos) {
        this.commandeRepos = commandeRepos;
        this.ligneCommandeRepos = ligneCommandeRepos;
        this.articleRepos = articleRepos;
        this.userRepos = userRepos;
    }


    @Timed(value = "orderLine.creation.time", description = "Time taken to create a command line")
    @Counted(value = "orderLine.creation.count", description="number of calling create command method")
    public LigneCommande create_Lcommande(Long article_Id, int nmbr) {
        if (articleRepos.existsArticleById(article_Id)) {
            Article artcl = articleRepos.findArticleById(article_Id);
            LigneCommande lcmd = new LigneCommande();
            lcmd.setNmbr(nmbr);
            lcmd.setArticle(artcl);
            ligneCommandeRepos.save(lcmd);
            return lcmd;
        }
        else {
            throw new IllegalArgumentException("Article with id " + article_Id + " doesn't exist" );
        }
    }

    public double calculateLcmdTot(LigneCommande lcmd){
        return lcmd.getArticle().getPrix() * lcmd.getNmbr();
    }



    @Override
    @Timed(value = "order.creation.time", description = "Time taken to create commande")
    @Counted(value = "order.creation.count", description="number of calling create command method")
    public Commande create(Long user_id, List<Long> ligne_commande_id){
        Commande cmd = new Commande();
        double tot = 0;

        if(userRepos.existsById(user_id)){
            AppUser principal = userRepos.findAppUserById(user_id);
            List<LigneCommande> addingList = new ArrayList<>();

            for(Long lcmd_id : ligne_commande_id ) {
                if(ligneCommandeRepos.existsById(lcmd_id)) {
                    LigneCommande lcmd = ligneCommandeRepos.findLigneCommandeById(lcmd_id);
                    tot += calculateLcmdTot(lcmd); // calcul du total de la commande
                    addingList.add(lcmd);
                }
            }
            cmd.setUser(principal);
            cmd.setLignes(addingList);
            cmd.setCreation(new Date());
            cmd.setValidated(false);
            cmd.setTotal(tot);
            commandeRepos.save(cmd);
            return cmd;
        }
        else {
            throw new IllegalArgumentException("User with id " + user_id + " doesn't exist" );
        }
    }

    public List<Commande> getuserCommande(Long user_id) {
        if(userRepos.existsById(user_id)){
            return commandeRepos.findCommandesByUserId(user_id);
        }
        else {
            throw new IllegalArgumentException("User with id " + user_id + " doesn't exist" );
        }
    }

    public Commande getCommande(Long Id) {
        return commandeRepos.findCommandeById(Id);
    }

    @Timed(value = "orderlist.fetching.time", description = "Time taken to fetch all commande")
    public List<Commande> getAll() {
        return commandeRepos.findAll();
    }








}
