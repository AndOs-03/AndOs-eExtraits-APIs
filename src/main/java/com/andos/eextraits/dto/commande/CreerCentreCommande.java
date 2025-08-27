package com.andos.eextraits.dto.commande;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Anderson Ouattara 2025-08-27
 */
public record CreerCentreCommande(
    @NotBlank(message = "Le nom du centre est obligatoire !")
    String nom
) {

}
