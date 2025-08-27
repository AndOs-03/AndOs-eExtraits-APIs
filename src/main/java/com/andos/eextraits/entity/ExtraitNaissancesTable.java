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
public class ExtraitNaissancesTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int annee;
  private String numeroRegistre;
  private LocalDate dateRegistre;
  private String registre;
  private LocalDate dateMariage;
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
}
