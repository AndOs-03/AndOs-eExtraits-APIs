package com.andos.eextraits.dto.commande;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anderson Ouattara 2025-08-28
 */
@Getter
@Setter
@NoArgsConstructor
public class ModifierExtraitDecesCommande extends CreerExtraitDecesCommande {

  @NotNull(message = "L'identifiant est obligatoire !")
  private Long id;
}
