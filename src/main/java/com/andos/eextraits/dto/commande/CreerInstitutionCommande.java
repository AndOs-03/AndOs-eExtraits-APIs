package com.andos.eextraits.dto.commande;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Anderson Ouattara 2025-08-28
 */
public record CreerInstitutionCommande(
    Long id,
    @NotBlank(message = "Le département est obligatoire !")
    String departement,
    @NotBlank(message = "Le centre d'État civil est obligatoire !")
    String centreEtatCivil,
    @NotBlank(message = "L'État civil est obligatoire !")
    String etatCivil,
    @NotBlank(message = "Le tribunal est obligatoire !")
    String tribunal,
    @NotBlank(message = "La ville est obligatoire !")
    String ville,
    String officier,
    String titreOfficier
) {

}
