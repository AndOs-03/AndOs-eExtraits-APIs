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
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anderson Ouattara 2025-08-27
 */
@Entity
@Table(name = "extrait_deces")
@Getter
@Setter
@NoArgsConstructor
public class ExtraitDecesTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  public ExtraitDecesTable(ExtraitDecesBuilder builder) {
    this.id = builder.id;
    this.annee = builder.annee;
    this.numeroRegistre = builder.numeroRegistre;
    this.dateRegistre = builder.dateRegistre;
    this.registre = builder.registre;
    this.dateDeces = builder.dateDeces;
    this.lieuDeces = builder.lieuDeces;
    this.nom = builder.nom;
    this.prenoms = builder.prenoms;
    this.nationalite = builder.nationalite;
    this.profession = builder.profession;
    this.domicile = builder.domicile;
    this.dateNaissance = builder.dateNaissance;
    this.lieuNaissance = builder.lieuNaissance;
    this.etatCivil = builder.etatCivil;
    this.centreEtatCivil = builder.centreEtatCivil;
    this.registreN = builder.registreN;
    this.situationMatrimoniale = builder.situationMatrimoniale;
    this.pere = builder.pere;
    this.mere = builder.mere;
  }

  @Getter
  @Setter
  public static class ExtraitDecesBuilder {

    private Long id;
    private int annee;
    private String numeroRegistre;
    private LocalDate dateRegistre;
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
    private SituationMatrimoniale situationMatrimoniale;
    private ParentExtrait pere;
    private ParentExtrait mere;

    public ExtraitDecesBuilder id(Long id) {
      this.id = id;
      return this;
    }

    public ExtraitDecesBuilder annee(int annee) {
      this.annee = annee;
      return this;
    }

    public ExtraitDecesBuilder numeroRegistre(String numeroRegistre) {
      this.numeroRegistre = numeroRegistre;
      return this;
    }

    public ExtraitDecesBuilder dateRegistre(LocalDate dateRegistre) {
      this.dateRegistre = dateRegistre;
      return this;
    }

    public ExtraitDecesBuilder registre(String registre) {
      this.registre = registre;
      return this;
    }

    public ExtraitDecesBuilder dateDeces(LocalDate dateDeces) {
      this.dateDeces = dateDeces;
      return this;
    }

    public ExtraitDecesBuilder lieuDeces(String lieuDeces) {
      this.lieuDeces = lieuDeces;
      return this;
    }

    public ExtraitDecesBuilder nom(String nom) {
      this.nom = nom;
      return this;
    }

    public ExtraitDecesBuilder prenoms(String prenoms) {
      this.prenoms = prenoms;
      return this;
    }

    public ExtraitDecesBuilder nationalite(String nationalite) {
      this.nationalite = nationalite;
      return this;
    }

    public ExtraitDecesBuilder profession(String profession) {
      this.profession = profession;
      return this;
    }

    public ExtraitDecesBuilder domicile(String domicile) {
      this.domicile = domicile;
      return this;
    }

    public ExtraitDecesBuilder dateNaissance(LocalDate dateNaissance) {
      this.dateNaissance = dateNaissance;
      return this;
    }

    public ExtraitDecesBuilder lieuNaissance(String lieuNaissance) {
      this.lieuNaissance = lieuNaissance;
      return this;
    }

    public ExtraitDecesBuilder etatCivil(String etatCivil) {
      this.etatCivil = etatCivil;
      return this;
    }

    public ExtraitDecesBuilder centreEtatCivil(String centreEtatCivil) {
      this.centreEtatCivil = centreEtatCivil;
      return this;
    }

    public ExtraitDecesBuilder registreN(String registreN) {
      this.registreN = registreN;
      return this;
    }

    public ExtraitDecesBuilder situationMatrimoniale(SituationMatrimoniale situationMatrimoniale) {
      this.situationMatrimoniale = situationMatrimoniale;
      return this;
    }

    public ExtraitDecesBuilder pere(ParentExtrait pere) {
      this.pere = pere;
      return this;
    }

    public ExtraitDecesBuilder mere(ParentExtrait mere) {
      this.mere = mere;
      return this;
    }

    public ExtraitDecesTable build() {
      return new ExtraitDecesTable(this);
    }
  }
}
