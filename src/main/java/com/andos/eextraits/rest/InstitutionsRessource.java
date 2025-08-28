package com.andos.eextraits.rest;

import com.andos.eextraits.dto.commande.CreerInstitutionCommande;
import com.andos.eextraits.dto.vm.InstitutionVM;
import com.andos.eextraits.service.InstitutionsService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Anderson Ouattara 2025-08-28
 */
@RestController
@RequestMapping("/api/institutions")
public class InstitutionsRessource {

  private final InstitutionsService institutionsService;

  public InstitutionsRessource(InstitutionsService institutionsService) {
    this.institutionsService = institutionsService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public void editer(@Valid @RequestBody CreerInstitutionCommande commande) {
    this.institutionsService.editer(commande);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void supprimer(@PathVariable("id") Long id) {
    this.institutionsService.supprimer(id);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<InstitutionVM> recupererParId(@PathVariable("id") Long id) {
    InstitutionVM institutionVm = this.institutionsService.recupererParId(id);
    return new ResponseEntity<>(institutionVm, HttpStatus.OK);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<InstitutionVM>> lister() {
    List<InstitutionVM> institutionVms = this.institutionsService.lister();
    return new ResponseEntity<>(institutionVms, HttpStatus.OK);
  }
}
