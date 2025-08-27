package com.andos.eextraits.dto.commande;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author Anderson Ouattara 2025-08-27
 */
public record ModifierCentreCommande(
    @NotNull(message = "L'identifiant est obligatoire !")
    Long id,
    @NotBlank(message = "Le nom du centre est obligatoire !")
    String nom
) {

}
