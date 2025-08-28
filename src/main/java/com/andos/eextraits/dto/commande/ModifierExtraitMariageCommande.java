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
public class ModifierExtraitMariageCommande extends CreerExtraitMariageCommande {

  @NotNull(message = "L'identifiant est obligatoire !")
  private Long id;
}
