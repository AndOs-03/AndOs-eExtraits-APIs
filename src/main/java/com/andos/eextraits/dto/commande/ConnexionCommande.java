package com.andos.eextraits.dto.commande;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Anderson Ouattara 2025-09-24
 */
public record ConnexionCommande(
    @NotBlank(message = "Le nom d'utilisateur est obligatoire !")
    String nomUtilisateur,
    @NotBlank(message = "Le mot de passe est obligatoire !")
    String motPasse
) {

}
