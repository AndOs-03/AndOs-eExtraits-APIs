package com.andos.eextraits.rest;

import com.andos.eextraits.dto.commande.CreerExtraitNaissanceCommande;
import com.andos.eextraits.dto.commande.ModifierExtraitNaissanceCommande;
import com.andos.eextraits.dto.vm.ExtraitNaissanceDetailsVM;
import com.andos.eextraits.dto.vm.ExtraitNaissanceEssentielVM;
import com.andos.eextraits.service.ExtraitsNaissancesService;
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
 * @author Anderson Ouattara 2025-08-29
 */
@RestController
@RequestMapping("/api/extraits-naissances")
public class ExtraitsNaissancesRessource {

  private final ExtraitsNaissancesService extraitsNaissancesService;

  public ExtraitsNaissancesRessource(ExtraitsNaissancesService extraitsNaissancesService) {
    this.extraitsNaissancesService = extraitsNaissancesService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void creer(@Valid @RequestBody CreerExtraitNaissanceCommande commande) {
    this.extraitsNaissancesService.creer(commande);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public void modifier(@Valid @RequestBody ModifierExtraitNaissanceCommande commande) {
    this.extraitsNaissancesService.modifier(commande);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void supprimer(@PathVariable("id") Long id) {
    this.extraitsNaissancesService.supprimer(id);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ExtraitNaissanceDetailsVM> recupererParId(@PathVariable("id") Long id) {
    ExtraitNaissanceDetailsVM extaitVm = this.extraitsNaissancesService.recupererParId(id);
    return new ResponseEntity<>(extaitVm, HttpStatus.OK);
  }

  @GetMapping("/{id}/essentiel")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ExtraitNaissanceEssentielVM> recupererParIdEssentiel(
      @PathVariable("id") Long id) {
    ExtraitNaissanceEssentielVM extaitVm = extraitsNaissancesService.recupererParIdEssentiel(id);
    return new ResponseEntity<>(extaitVm, HttpStatus.OK);
  }

  @GetMapping("/centre/{centreId}/recuperer-par-registre")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ExtraitNaissanceDetailsVM> recupererParRegistre(
      @RequestParam("registre") String registre, @PathVariable("centreId") Long centreId) {
    ExtraitNaissanceDetailsVM extaitVm = extraitsNaissancesService.recupererParRegistre(registre,
        centreId);
    return new ResponseEntity<>(extaitVm, HttpStatus.OK);
  }

  @GetMapping("/centre/{centreId}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<ExtraitNaissanceEssentielVM>> lister(
      @PathVariable("centreId") Long centreId) {
    List<ExtraitNaissanceEssentielVM> extraitVms = this.extraitsNaissancesService.lister(centreId);
    return new ResponseEntity<>(extraitVms, HttpStatus.OK);
  }
}
