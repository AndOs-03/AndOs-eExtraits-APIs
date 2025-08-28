package com.andos.eextraits.rest;

import com.andos.eextraits.dto.commande.CreerExtraitDecesCommande;
import com.andos.eextraits.dto.commande.ModifierExtraitDecesCommande;
import com.andos.eextraits.dto.vm.ExtraitDecesDetailsVM;
import com.andos.eextraits.dto.vm.ExtraitDecesEssentielVM;
import com.andos.eextraits.service.ExtraitsDecesService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Anderson Ouattara 2025-08-28
 */
@RestController
@RequestMapping("/api/extraits-deces")
public class ExtraitsDecesRessource {

  private final ExtraitsDecesService extraitsDecesService;

  public ExtraitsDecesRessource(ExtraitsDecesService extraitsDecesService) {
    this.extraitsDecesService = extraitsDecesService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void creer(@Valid @RequestBody CreerExtraitDecesCommande commande) {
    this.extraitsDecesService.creer(commande);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public void modifier(@Valid @RequestBody ModifierExtraitDecesCommande commande) {
    this.extraitsDecesService.modifier(commande);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void supprimer(@PathVariable("id") Long id) {
    this.extraitsDecesService.supprimer(id);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ExtraitDecesDetailsVM> recupererParId(@PathVariable("id") Long id) {
    ExtraitDecesDetailsVM centreVm = this.extraitsDecesService.recupererParId(id);
    return new ResponseEntity<>(centreVm, HttpStatus.OK);
  }

  @GetMapping("/recuperer-par-registre")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ExtraitDecesDetailsVM> recupererParRegistre(
      @RequestParam("registre") String registre) {
    ExtraitDecesDetailsVM extraitDecesVms = extraitsDecesService.recupererParRegistre(registre);
    return new ResponseEntity<>(extraitDecesVms, HttpStatus.OK);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<ExtraitDecesEssentielVM>> lister() {
    List<ExtraitDecesEssentielVM> extraitDecesVms = this.extraitsDecesService.lister();
    return new ResponseEntity<>(extraitDecesVms, HttpStatus.OK);
  }
}
