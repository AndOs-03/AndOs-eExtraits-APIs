package com.andos.eextraits.dto.vm;

import com.andos.eextraits.dto.objetvaleur.PersonneExtraitNaissance;
import java.time.LocalDate;

/**
 * @author Anderson Ouattara 2025-08-28
 */
public record ExtraitNaissanceDetailsVM(
    Long id,
    int annee,
    String numeroRegistre,
    LocalDate dateRegistre,
    String registre,
    String numeroJugementSupletif,
    LocalDate dateJugementSupletif,
    String tribunalJugementSupletif,
    boolean extraitTypeTPI,
    String etatCivil,
    String centreEtatCivil,
    String registreN,
    boolean nouveauModel,
    CentreVM centre,
    PersonneExtraitNaissance personne
) {

}
