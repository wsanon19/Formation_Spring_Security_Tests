package com.security.forma_security.Service.UserService;

import com.security.forma_security.Model.Commande;

import java.util.List;

public interface CommandeService {

    Commande create(Long user_id, List<Long> ligne_commande_id);
}
