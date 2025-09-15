package com.andos.eextraits.dto.commande;

import com.andos.eextraits.dto.objetvaleur.ParentExtrait;
import com.andos.eextraits.dto.objetvaleur.SituationMatrimoniale;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anderson Ouattara 2025-08-28
 */
@Getter
@Setter
@NoArgsConstructor
public class CreerExtraitDecesCommande {

  @Min(value = 1, message = "L'année est obligatoire !")
  private int annee;
  @NotBlank(message = "Le numéro d'acte est obligatoire !")
  private String numeroRegistre;
  @NotNull(message = "La date du registre est obligatoire !")
  private LocalDate dateRegistre;
  @NotNull(message = "La date du décès est obligatoire !")
  private LocalDate dateDeces;
  private String lieuDeces;
  @NotBlank(message = "Le nom est obligatoire !")
  private String nom;
  @NotBlank(message = "Le prénom est obligatoire !")
  private String prenoms;
  private String nationalite;
  private String profession;
  private String domicile;
  @NotNull(message = "La date de naissance est obligatoire !")
  private LocalDate dateNaissance;
  private String lieuNaissance;
  @NotBlank(message = "L'État Civil est obligatoire !")
  private String etatCivil;
  @NotBlank(message = "Le centre d'État Civil est obligatoire !")
  private String centreEtatCivil;
  private String registreN;
  @NotNull(message = "Choisir la situation matrimoniale !")
  private SituationMatrimoniale situationMatrimoniale;
  private ParentExtrait pere;
  private ParentExtrait mere;
}
