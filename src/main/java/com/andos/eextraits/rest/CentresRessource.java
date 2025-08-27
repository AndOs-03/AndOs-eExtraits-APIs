package com.andos.eextraits.rest;

import com.andos.eextraits.dto.commande.CreerCentreCommande;
import com.andos.eextraits.dto.commande.ModifierCentreCommande;
import com.andos.eextraits.dto.vm.CentreVM;
import com.andos.eextraits.service.CentresService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Anderson Ouattara 2025-08-27
 */
@RestController
@RequestMapping("/api/centres")
public class CentresRessource {

  private final CentresService centresService;

  public CentresRessource(CentresService centresService) {
    this.centresService = centresService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void creer(@Valid @RequestBody CreerCentreCommande commande) {
    this.centresService.creer(commande);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public void modifier(@Valid @RequestBody ModifierCentreCommande commande) {
    this.centresService.modifier(commande);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void supprimer(@PathVariable("id") Long id) {
    this.centresService.supprimer(id);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CentreVM> recupererParId(@PathVariable("id") Long id) {
    CentreVM centreVm = this.centresService.recupererParId(id);
    return new ResponseEntity<>(centreVm, HttpStatus.OK);
  }

  @GetMapping("/recuperer-par-nom/{nom}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CentreVM> recupererParId(@PathVariable("nom") String nom) {
    CentreVM centreVm = this.centresService.recupererParNom(nom);
    return new ResponseEntity<>(centreVm, HttpStatus.OK);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<CentreVM>> lister() {
    List<CentreVM> centreVms = this.centresService.lister();
    return new ResponseEntity<>(centreVms, HttpStatus.OK);
  }
}
