package com.andos.eextraits.dto.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>Regroupe les informations personnelles d'une personne morale sur son extrait de mariage.</p>
 *
 * @author Anderson Ouattara 2025-09-24
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonneExtraitMariageExportVM {

  private String nomPrenoms;
  private String dateNaissance;
  private String lieuNaissance;
  private String domicile;
  private String pere;
  private String mere;
}
