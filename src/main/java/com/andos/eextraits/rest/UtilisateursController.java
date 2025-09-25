package com.andos.eextraits.rest;

import com.andos.eextraits.dto.vm.UtilisateurVM;
import com.andos.eextraits.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Anderson Ouattara 2025-09-25
 */
@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateursController {

  private final UtilisateurService utilisateurService;

  public UtilisateursController(UtilisateurService utilisateurService) {
    this.utilisateurService = utilisateurService;
  }

  @GetMapping("/recuperer-par-username")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UtilisateurVM> utilisateurParUserName(
      @RequestParam("username") String username) {
    UtilisateurVM utilisateurVm = this.utilisateurService.utilisateurParUserName(username);
    return ResponseEntity.ok(utilisateurVm);
  }
}
