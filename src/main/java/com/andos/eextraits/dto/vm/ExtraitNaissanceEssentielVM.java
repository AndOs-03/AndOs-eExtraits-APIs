package com.andos.eextraits.dto.vm;

import java.time.LocalDate;

/**
 * @author Anderson Ouattara 2025-08-28
 */
public record ExtraitNaissanceEssentielVM(
    Long id,
    String numeroJugementSupletif,
    String registre,
    LocalDate dateNaissance,
    String nom,
    String prenoms,
    String lieuNaissance,
    String registreN
) {

}
