package com.andos.eextraits.service;

import com.andos.eextraits.dto.commande.ConnexionCommande;
import com.andos.eextraits.dto.commande.CreerUtilisateurCommande;
import com.andos.eextraits.dto.vm.JwtToken;

/**
 * @author Anderson Ouattara 2025-09-24
 */
public interface UtilisateurService {

  void creer(CreerUtilisateurCommande commande);

  JwtToken connexion(ConnexionCommande commande);
}
