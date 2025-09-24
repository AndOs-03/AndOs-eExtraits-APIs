package com.andos.eextraits.service;

import com.andos.eextraits.dto.commande.ConnexionCommande;
import com.andos.eextraits.dto.commande.CreerUtilisateurCommande;

/**
 * @author Anderson Ouattara 2025-09-24
 */
public interface UtilisateurService {

  void creer(CreerUtilisateurCommande commande);

  String connexion(ConnexionCommande commande);
}
