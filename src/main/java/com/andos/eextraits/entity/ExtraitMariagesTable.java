package com.andos.eextraits.entity;

import com.andos.eextraits.dto.objetvaleur.PersonneExtraitMariage;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
@Table(name = "extrait_mariages")
@Getter
@Setter
public class ExtraitMariagesTable {

  @Id
  @GeneratedValue
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

  @Embedded
  private PersonneExtraitMariage personne;
}
