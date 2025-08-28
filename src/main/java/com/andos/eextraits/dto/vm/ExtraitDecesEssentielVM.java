package com.andos.eextraits.dto.vm;

import java.time.LocalDate;

/**
 * @author Anderson Ouattara 2025-08-28
 */
public record ExtraitDecesEssentielVM(
    Long id,
    String registre,
    LocalDate dateDeces,
    String lieuDeces,
    String nom,
    String prenoms,
    String nationalite,
    String profession,
    String domicile,
    String registreN
) {

}
