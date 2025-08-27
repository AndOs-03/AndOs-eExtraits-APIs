package com.andos.eextraits.dto.objetvaleur;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anderson Ouattara 2025-08-27
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ParentExtrait {

  private String nomPrenoms;
  @Enumerated(EnumType.STRING)
  private TypeParent typeParent;
}
