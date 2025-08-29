package com.andos.eextraits.entity;

import com.andos.eextraits.dto.objetvaleur.MentionsEventuelle;
import com.andos.eextraits.dto.objetvaleur.ParentExtrait;
import com.andos.eextraits.dto.objetvaleur.Sexe;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Anderson Ouattara 2025-08-27
 */
@Entity
@Table(name = "personne_extrait_naissances")
@Getter
@Setter
public class PersonneExtraitNaissanceTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nom;
  private String prenoms;
  private LocalDate dateNaissance;
  private String lieuNaissance;
  @Enumerated(EnumType.STRING)
  private Sexe sexe;

  @Embedded
  private MentionsEventuelle mentionsEventuelle;

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
