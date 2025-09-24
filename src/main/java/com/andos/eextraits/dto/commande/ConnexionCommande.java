package com.andos.eextraits.dto.commande;

import jakarta.validation.constraints.NotNull;

/**
 * @author Anderson Ouattara 2025-09-24
 */
public record ConnexionCommande(
    @NotNull(message = "Le nom d'utilisateur est obligatoire !")
    String nomUtilisateur,
    @NotNull(message = "Le mot de passe est obligatoire !")
    String motPasse
) {

}
