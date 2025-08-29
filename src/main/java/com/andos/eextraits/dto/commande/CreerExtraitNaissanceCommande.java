package com.andos.eextraits.dto.commande;

import com.andos.eextraits.dto.objetvaleur.PersonneExtraitNaissance;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Anderson Ouattara 2025-08-29
 */
@Getter
@Setter
public class CreerExtraitNaissanceCommande {

  @Min(value = 1, message = "L'année est obligatoire !")
  private int annee;
  @NotBlank(message = "Le numéro de registre est obligatoire !")
  private String numeroRegistre;
  @NotNull(message = "La date du registre est obligatoire !")
  private LocalDate dateRegistre;
  @NotBlank(message = "L'État Civil est obligatoire !")
  private String etatCivil;
  @NotBlank(message = "Le centre d'État Civil est obligatoire !")
  private String centreEtatCivil;
  private String registreN;
  private String numeroJugementSupletif;
  private LocalDate dateJugementSupletif;
  private String tribunalJugementSupletif;
  private boolean extraitTypeTPI;
  private boolean nouveauModel;
  @NotNull(message = "L'identifiant du centre est obligatoire !")
  private Long centreId;
  @NotNull(message = "Renseignez les informations sur la personnalité !")
  @Valid
  private PersonneExtraitNaissance personne;
}
