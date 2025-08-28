package com.andos.eextraits.dto.vm;

import com.andos.eextraits.dto.objetvaleur.ParentExtrait;
import com.andos.eextraits.dto.objetvaleur.SituationMatrimoniale;
import java.time.LocalDate;

/**
 * @author Anderson Ouattara 2025-08-28
 */
public record ExtraitDecesDetailsVM(
    Long id,
    int annee,
    String numeroRegistre,
    LocalDate dateRegistre,
    String registre,
    LocalDate dateDeces,
    String lieuDeces,
    String nom,
    String prenoms,
    String nationalite,
    String profession,
    String domicile,
    LocalDate dateNaissance,
    String lieuNaissance,
    String etatCivil,
    String centreEtatCivil,
    String registreN,
    SituationMatrimoniale situationMatrimoniale,
    ParentExtrait pere,
    ParentExtrait mere
) {

}
