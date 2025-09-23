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

  int annee;
  String registre;
  String dateDeces;
  String lieuDeces;
  String nom;
  String prenoms;
  String nationalite;
  String profession;
  String domicile;
  String dateNaissance;
  String lieuNaissance;
  String etatCivil;
  String centreEtatCivil;
  String registreN;
  String situationMatrimoniale;
  String pere;
  String mere;
}
