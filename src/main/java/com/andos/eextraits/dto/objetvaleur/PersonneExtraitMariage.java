package com.andos.eextraits.dto.objetvaleur;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>Regroupe les informations personnelles d'une personne morale sur son extrait de mariage.</p>
 *
 * @author Anderson Ouattara 2025-08-27
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PersonneExtraitMariage {

  private String nomPrenoms;
  private LocalDate dateNaissance;
  private String lieuNaissance;
  private String domicile;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "nomPrenoms", column = @Column(name = "nom_prenoms_pere")),
      @AttributeOverride(name = "typeParent", column = @Column(name = "type_parent_pere"))
  })
  private ParentExtrait pere;
  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "nomPrenoms", column = @Column(name = "nom_prenoms_mere")),
      @AttributeOverride(name = "typeParent", column = @Column(name = "type_parent_mere"))
  })
  private ParentExtrait mere;
}
