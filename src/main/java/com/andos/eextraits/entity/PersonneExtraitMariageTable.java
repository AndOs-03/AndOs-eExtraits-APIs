package com.andos.eextraits.entity;

import com.andos.eextraits.dto.objetvaleur.ParentExtrait;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Anderson Ouattara 2025-08-27
 */
@Entity
@Table(name = "personne_extrait_mariage")
@Getter
@Setter
public class PersonneExtraitMariageTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank(message = "Le nom est obligatoire !")
  private String nomPrenoms;
  @NotNull(message = "La date de naissance est obligatoire !")
  private LocalDate dateNaissance;
  @NotBlank(message = "Le lieu de naissance est obligatoire !")
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
