package com.andos.eextraits.dto.objetvaleur;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>Regroupe les informations personnelles d'une personne morale sur son extrait de
 * naissance.</p>
 *
 * @author Anderson Ouattara 2025-08-27
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonneExtraitNaissance {

  private Long id;
  private String nom;
  private String prenoms;
  private LocalDate dateNaissance;
  private String lieuNaissance;
  private boolean mariage;
  private LocalDate dateMariage;
  private String lieuMariage;
  private String epouOuEpouse;
  private boolean divorce;
  private LocalDate dateDeces;
  private String lieuDeces;
  private String registre;
  private Sexe sexe;
  private ParentExtrait pere;
  private ParentExtrait mere;
}
