package com.andos.eextraits.rest;

import com.andos.eextraits.dto.commande.ConnexionCommande;
import com.andos.eextraits.dto.commande.CreerUtilisateurCommande;
import com.andos.eextraits.service.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Anderson Ouattara 2025-09-24
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final UtilisateurService utilisateurService;

  public AuthController(UtilisateurService utilisateurService) {
    this.utilisateurService = utilisateurService;
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void register(@Valid @RequestBody CreerUtilisateurCommande commande) {
    this.utilisateurService.creer(commande);
  }

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public String login(@Valid @RequestBody ConnexionCommande commande) {
    return this.utilisateurService.connexion(commande);
  }
}
