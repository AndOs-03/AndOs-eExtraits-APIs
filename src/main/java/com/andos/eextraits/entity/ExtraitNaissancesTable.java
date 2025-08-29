package com.andos.eextraits.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anderson Ouattara 2025-08-27
 */
@Entity
@Table(name = "extrait_naissances", uniqueConstraints = {
    @UniqueConstraint(name = "uk_extrait_registre_centre", columnNames = {"registre", "centre_id"})
})
@Getter
@Setter
@NoArgsConstructor
public class ExtraitNaissancesTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int annee;
  private String numeroRegistre;
  private LocalDate dateRegistre;
  private String registre;
  private String numeroJugementSupletif;
  private LocalDate dateJugementSupletif;
  private String tribunalJugementSupletif;
  private boolean extraitTypeTPI;
  private String etatCivil;
  private String centreEtatCivil;
  private String registreN;
  private boolean nouveauModel;

  @ManyToOne(optional = false)
  private CentresTable centre;
  @OneToOne(cascade = CascadeType.ALL)
  private PersonneExtraitNaissanceTable personne;

  public ExtraitNaissancesTable(ExtraitNaissancesBuilder builder) {
    this.id = builder.id;
    this.annee = builder.annee;
    this.numeroRegistre = builder.numeroRegistre;
    this.dateRegistre = builder.dateRegistre;
    this.registre = builder.registre;
    this.numeroJugementSupletif = builder.numeroJugementSupletif;
    this.dateJugementSupletif = builder.dateJugementSupletif;
    this.tribunalJugementSupletif = builder.tribunalJugementSupletif;
    this.extraitTypeTPI = builder.extraitTypeTPI;
    this.etatCivil = builder.etatCivil;
    this.centreEtatCivil = builder.centreEtatCivil;
    this.registreN = builder.registreN;
    this.nouveauModel = builder.nouveauModel;
    this.centre = builder.centre;
    this.personne = builder.personne;
  }

  @Getter
  @Setter
  public static class ExtraitNaissancesBuilder {

    private Long id;
    private int annee;
    private String numeroRegistre;
    private LocalDate dateRegistre;
    private String registre;
    private String numeroJugementSupletif;
    private LocalDate dateJugementSupletif;
    private String tribunalJugementSupletif;
    private boolean extraitTypeTPI;
    private String etatCivil;
    private String centreEtatCivil;
    private String registreN;
    private boolean nouveauModel;
    private CentresTable centre;
    private PersonneExtraitNaissanceTable personne;

    public ExtraitNaissancesTable.ExtraitNaissancesBuilder id(Long id) {
      this.id = id;
      return this;
    }

    public ExtraitNaissancesTable.ExtraitNaissancesBuilder annee(int annee) {
      this.annee = annee;
      return this;
    }

    public ExtraitNaissancesTable.ExtraitNaissancesBuilder numeroRegistre(String numeroRegistre) {
      this.numeroRegistre = numeroRegistre;
      return this;
    }

    public ExtraitNaissancesTable.ExtraitNaissancesBuilder dateRegistre(LocalDate dateRegistre) {
      this.dateRegistre = dateRegistre;
      return this;
    }

    public ExtraitNaissancesTable.ExtraitNaissancesBuilder registre(String registre) {
      this.registre = registre;
      return this;
    }

    public ExtraitNaissancesTable.ExtraitNaissancesBuilder numeroJugementSupletif(
        String numeroJugementSupletif) {
      this.numeroJugementSupletif = numeroJugementSupletif;
      return this;
    }

    public ExtraitNaissancesTable.ExtraitNaissancesBuilder dateJugementSupletif(
        LocalDate dateJugementSupletif) {
      this.dateJugementSupletif = dateJugementSupletif;
      return this;
    }

    public ExtraitNaissancesTable.ExtraitNaissancesBuilder tribunalJugementSupletif(
        String tribunalJugementSupletif) {
      this.tribunalJugementSupletif = tribunalJugementSupletif;
      return this;
    }

    public ExtraitNaissancesTable.ExtraitNaissancesBuilder extraitTypeTPI(boolean extraitTypeTPI) {
      this.extraitTypeTPI = extraitTypeTPI;
      return this;
    }

    public ExtraitNaissancesTable.ExtraitNaissancesBuilder etatCivil(String etatCivil) {
      this.etatCivil = etatCivil;
      return this;
    }

    public ExtraitNaissancesTable.ExtraitNaissancesBuilder centreEtatCivil(String centreEtatCivil) {
      this.centreEtatCivil = centreEtatCivil;
      return this;
    }

    public ExtraitNaissancesTable.ExtraitNaissancesBuilder registreN(String registreN) {
      this.registreN = registreN;
      return this;
    }

    public ExtraitNaissancesTable.ExtraitNaissancesBuilder nouveauModel(boolean nouveauModel) {
      this.nouveauModel = nouveauModel;
      return this;
    }

    public ExtraitNaissancesTable.ExtraitNaissancesBuilder centre(CentresTable centre) {
      this.centre = centre;
      return this;
    }

    public ExtraitNaissancesTable.ExtraitNaissancesBuilder personne(
        PersonneExtraitNaissanceTable personne) {
      this.personne = personne;
      return this;
    }

    public ExtraitNaissancesTable build() {
      return new ExtraitNaissancesTable(this);
    }
  }
}
