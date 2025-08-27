package com.andos.eextraits.entity;

import com.andos.eextraits.dto.objetvaleur.ParentExtrait;
import com.andos.eextraits.dto.objetvaleur.SituationMatrimoniale;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Anderson Ouattara 2025-08-27
 */
@Entity
@Table(name = "extrait_deces")
@Getter
@Setter
public class ExtraitDecesTable {

  @Id
  @GeneratedValue
  private Long id;
  private int annee;
  private String numeroRegistre;
  private LocalDate dateRegistre;
  @Column(unique = true)
  private String registre;
  private LocalDate dateDeces;
  private String lieuDeces;
  private String nom;
  private String prenoms;
  private String nationalite;
  private String profession;
  private String domicile;
  private LocalDate dateNaissance;
  private String lieuNaissance;
  private String etatCivil;
  private String centreEtatCivil;
  private String registreN;
  @Enumerated(EnumType.STRING)
  private SituationMatrimoniale situationMatrimoniale;

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
