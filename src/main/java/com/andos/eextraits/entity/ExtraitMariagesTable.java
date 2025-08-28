package com.andos.eextraits.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anderson Ouattara 2025-08-27
 */
@Entity
@Table(name = "extrait_mariages")
@Getter
@Setter
@NoArgsConstructor
public class ExtraitMariagesTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int annee;
  private String numeroRegistre;
  private LocalDate dateRegistre;
  @Column(unique = true)
  private String registre;
  private LocalDate dateMariage;
  private String etatCivil;
  private String centreEtatCivil;
  private String registreN;

  @OneToOne(cascade = CascadeType.ALL)
  private PersonneExtraitMariageTable epoux;
  @OneToOne(cascade = CascadeType.ALL)
  private PersonneExtraitMariageTable epouse;

  public ExtraitMariagesTable(ExtraitMariagesBuilder builder) {
    this.id = builder.id;
    this.annee = builder.annee;
    this.numeroRegistre = builder.numeroRegistre;
    this.dateRegistre = builder.dateRegistre;
    this.registre = builder.registre;
    this.dateMariage = builder.dateMariage;
    this.etatCivil = builder.etatCivil;
    this.centreEtatCivil = builder.centreEtatCivil;
    this.registreN = builder.registreN;
    this.epoux = builder.epoux;
    this.epouse = builder.epouse;
  }

  @Getter
  @Setter
  public static class ExtraitMariagesBuilder {

    private Long id;
    private int annee;
    private String numeroRegistre;
    private LocalDate dateRegistre;
    private String registre;
    private LocalDate dateMariage;
    private String etatCivil;
    private String centreEtatCivil;
    private String registreN;
    private PersonneExtraitMariageTable epoux;
    private PersonneExtraitMariageTable epouse;

    public ExtraitMariagesTable.ExtraitMariagesBuilder id(Long id) {
      this.id = id;
      return this;
    }

    public ExtraitMariagesTable.ExtraitMariagesBuilder annee(int annee) {
      this.annee = annee;
      return this;
    }

    public ExtraitMariagesTable.ExtraitMariagesBuilder numeroRegistre(String numeroRegistre) {
      this.numeroRegistre = numeroRegistre;
      return this;
    }

    public ExtraitMariagesTable.ExtraitMariagesBuilder dateRegistre(LocalDate dateRegistre) {
      this.dateRegistre = dateRegistre;
      return this;
    }

    public ExtraitMariagesTable.ExtraitMariagesBuilder dateMariage(LocalDate dateMariage) {
      this.dateMariage = dateMariage;
      return this;
    }

    public ExtraitMariagesTable.ExtraitMariagesBuilder registre(String registre) {
      this.registre = registre;
      return this;
    }

    public ExtraitMariagesTable.ExtraitMariagesBuilder etatCivil(String etatCivil) {
      this.etatCivil = etatCivil;
      return this;
    }

    public ExtraitMariagesTable.ExtraitMariagesBuilder centreEtatCivil(String centreEtatCivil) {
      this.centreEtatCivil = centreEtatCivil;
      return this;
    }

    public ExtraitMariagesTable.ExtraitMariagesBuilder registreN(String registreN) {
      this.registreN = registreN;
      return this;
    }

    public ExtraitMariagesTable.ExtraitMariagesBuilder epoux(PersonneExtraitMariageTable epoux) {
      this.epoux = epoux;
      return this;
    }

    public ExtraitMariagesTable.ExtraitMariagesBuilder epouse(PersonneExtraitMariageTable epouse) {
      this.epouse = epouse;
      return this;
    }

    public ExtraitMariagesTable build() {
      return new ExtraitMariagesTable(this);
    }
  }
}
