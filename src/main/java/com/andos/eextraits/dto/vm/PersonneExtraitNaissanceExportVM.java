package com.andos.eextraits.dto.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>Regroupe les informations personnelles d'une personne morale sur son extrait de
 * naissance.</p>
 *
 * @author Anderson Ouattara 2025-09-24
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonneExtraitNaissanceExportVM {

  private String nom;
  private String prenoms;
  private String dateNaissance;
  private String lieuNaissance;
  private MentionsEventuelleExportVM mentionsEventuelle;
  private String pere;
  private String mere;
}
