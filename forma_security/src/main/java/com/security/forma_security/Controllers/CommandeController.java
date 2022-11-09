package com.security.forma_security.Controllers;

import com.security.forma_security.Model.*;
import com.security.forma_security.Service.UserService.ArticleService;
import com.security.forma_security.Service.UserService.CommandeService;
import com.security.forma_security.Service.UserService.CommandeServiceImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/commande")
public class CommandeController {

    private final CommandeServiceImpl commandeServiceImpl;
    private final CommandeService commandeService;
    private final ArticleService articleService; // Juste pr les tests

    @GetMapping("/list")
    public ResponseEntity<List<Commande>> getCommandes(){
        return  ResponseEntity.ok().body(commandeServiceImpl.getAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Commande>> getUserCommandes(@PathVariable(value = "id") Long id ){
        return  ResponseEntity.ok().body(commandeServiceImpl.getuserCommande(id));
    }

    @GetMapping("/article/list")
    public ResponseEntity<List<Article>> getArticles(){
        return  ResponseEntity.ok().body(articleService.getArticles());
    }



    @PostMapping("/save")
    public ResponseEntity<Commande> saveCommande(@RequestBody CommandeForm form){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/commande/save").toUriString());


        List<Long> addinglcmd = new ArrayList<>();

        //creer les lignes de commandes et insertion des id dans le tableau de lignes
        for (LCommande lcmd : form.getLignescommandes()) {
            LigneCommande currentlcmd = commandeServiceImpl.create_Lcommande(lcmd.getArtcl_id(), lcmd.getNmbr());
            addinglcmd.add(currentlcmd.getId());
        }

        return  ResponseEntity.created(uri).body(commandeService.create(form.getUserid(),addinglcmd ));
    }

}


@Data
class CommandeForm {
    private List<LCommande> lignescommandes;
    private Long userid;

    public List<LCommande> getLignescommandes() {
        return lignescommandes;
    }

    public void setLignescommandes(List<LCommande> lignescommandes) {
        this.lignescommandes = lignescommandes;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}

@Data
class LCommande {
    private Long artcl_id;
    private int nmbr;
}




