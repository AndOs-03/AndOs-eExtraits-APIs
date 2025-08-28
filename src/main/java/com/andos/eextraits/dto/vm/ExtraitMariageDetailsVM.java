package com.andos.eextraits.dto.vm;

import com.andos.eextraits.dto.objetvaleur.PersonneExtraitMariage;
import java.time.LocalDate;

/**
 * @author Anderson Ouattara 2025-08-28
 */
public record ExtraitMariageDetailsVM(
    Long id,
    int annee,
    String numeroRegistre,
    LocalDate dateRegistre,
    String registre,
    LocalDate dateMariage,
    String etatCivil,
    String centreEtatCivil,
    String registreN,
    PersonneExtraitMariage epoux,
    PersonneExtraitMariage epouse
) {

}
