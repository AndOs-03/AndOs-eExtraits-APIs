package com.andos.eextraits.dto.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anderson Ouattara 2025-09-22
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExportExtraitDecesVM extends ExportExtraitVM {

  private int annee;
  private String dateDeces;
  private String lieuDeces;
  private String nom;
  private String prenoms;
  private String nationalite;
  private String profession;
  private String domicile;
  private String dateNaissance;
  private String lieuNaissance;
  private String situationMatrimoniale;
  private String pere;
  private String mere;
}
