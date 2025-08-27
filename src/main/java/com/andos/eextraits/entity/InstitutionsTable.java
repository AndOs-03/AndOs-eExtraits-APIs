package com.andos.eextraits.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>La classe Institutions regroupe les informations qui caractérisent l'entité qui génère
 * l'extrait.</p>
 *
 * @author Anderson Ouattara 2025-08-27
 */
@Entity
@Table(name = "institutions")
@Getter
@Setter
public class InstitutionsTable {

  @Id
  @GeneratedValue
  private Long id;
  private String departement;
  private String centreEtatCivil;
  private String etatCivil;
  private String tribunal;
  private String ville;
  private String officier;
  private String titreOfficier;
}
