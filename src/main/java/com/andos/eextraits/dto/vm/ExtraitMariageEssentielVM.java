package com.andos.eextraits.dto.vm;

import com.andos.eextraits.dto.objetvaleur.PersonneExtraitMariage;
import java.time.LocalDate;

/**
 * @author Anderson Ouattara 2025-08-28
 */
public record ExtraitMariageEssentielVM(
    Long id,
    String numeroRegistre,
    String registre,
    LocalDate dateMariage,
    String registreN,
    PersonneExtraitMariage epoux,
    PersonneExtraitMariage epouse
) {

}
