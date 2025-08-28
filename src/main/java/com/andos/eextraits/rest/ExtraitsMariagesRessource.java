package com.andos.eextraits.rest;

import com.andos.eextraits.dto.commande.CreerExtraitMariageCommande;
import com.andos.eextraits.dto.commande.ModifierExtraitMariageCommande;
import com.andos.eextraits.dto.vm.ExtraitMariageDetailsVM;
import com.andos.eextraits.dto.vm.ExtraitMariageEssentielVM;
import com.andos.eextraits.service.ExtraitsMariagesService;
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
@RequestMapping("/api/extraits-mariages")
public class ExtraitsMariagesRessource {

  private final ExtraitsMariagesService extraitsMariagesService;

  public ExtraitsMariagesRessource(ExtraitsMariagesService extraitsMariagesService) {
    this.extraitsMariagesService = extraitsMariagesService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void creer(@Valid @RequestBody CreerExtraitMariageCommande commande) {
    this.extraitsMariagesService.creer(commande);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public void modifier(@Valid @RequestBody ModifierExtraitMariageCommande commande) {
    this.extraitsMariagesService.modifier(commande);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void supprimer(@PathVariable("id") Long id) {
    this.extraitsMariagesService.supprimer(id);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ExtraitMariageDetailsVM> recupererParId(@PathVariable("id") Long id) {
    ExtraitMariageDetailsVM extaitVm = this.extraitsMariagesService.recupererParId(id);
    return new ResponseEntity<>(extaitVm, HttpStatus.OK);
  }

  @GetMapping("/recuperer-par-registre")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ExtraitMariageDetailsVM> recupererParRegistre(
      @RequestParam("registre") String registre) {
    ExtraitMariageDetailsVM extaitVm = extraitsMariagesService.recupererParRegistre(registre);
    return new ResponseEntity<>(extaitVm, HttpStatus.OK);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<ExtraitMariageEssentielVM>> lister() {
    List<ExtraitMariageEssentielVM> extraitVms = this.extraitsMariagesService.lister();
    return new ResponseEntity<>(extraitVms, HttpStatus.OK);
  }
}
