package com.andos.eextraits.dto.vm;

/**
 * @author Anderson Ouattara 2025-08-28
 */
public record InstitutionVM(
    Long id,
    String departement,
    String centreEtatCivil,
    String etatCivil,
    String tribunal,
    String ville,
    String officier,
    String titreOfficier
) {

}
