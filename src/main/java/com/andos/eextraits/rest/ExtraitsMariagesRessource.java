package com.andos.eextraits.rest;

import com.andos.eextraits.dto.commande.CreerExtraitMariageCommande;
import com.andos.eextraits.dto.commande.ModifierExtraitMariageCommande;
import com.andos.eextraits.dto.vm.ExtraitMariageDetailsVM;
import com.andos.eextraits.dto.vm.ExtraitMariageEssentielVM;
import com.andos.eextraits.service.ExtraitsMariagesService;
import com.andos.eextraits.service.GenererFichierExtraitService;
import jakarta.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.List;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
  private final GenererFichierExtraitService genererFichierExtraitService;

  public ExtraitsMariagesRessource(
      ExtraitsMariagesService extraitsMariagesService,
      GenererFichierExtraitService genererFichierExtraitService
  ) {
    this.extraitsMariagesService = extraitsMariagesService;
    this.genererFichierExtraitService = genererFichierExtraitService;
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

  @GetMapping("/{id}/generer-fichier-extrait")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Resource> genererFichierExtrait(
      @PathVariable Long id,
      @RequestParam("institutionId") Long institutionId,
      @RequestParam("centreId") Long centreId
  ) {
    ExtraitMariageDetailsVM extaitVm = this.extraitsMariagesService.recupererParId(id);
    ByteArrayOutputStream byteArray = this.genererFichierExtraitService.extraitMariage(extaitVm,
        institutionId, centreId);

    byte[] bytes = byteArray.toByteArray();
    ByteArrayResource byteArrayResource = new ByteArrayResource(bytes);
    String nomPrenoms = extaitVm.epoux().getNomPrenoms();
    String nomFichier = "extrait - mariage - " + nomPrenoms + ".pdf";

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nomFichier)
        .contentType(MediaType.APPLICATION_PDF)
        .body(byteArrayResource);
  }
}
