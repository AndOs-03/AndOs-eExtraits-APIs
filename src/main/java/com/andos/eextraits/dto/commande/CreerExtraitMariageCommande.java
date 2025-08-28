package com.andos.eextraits.dto.commande;

import com.andos.eextraits.dto.objetvaleur.PersonneExtraitMariage;
import jakarta.validation.Valid;
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
public class CreerExtraitMariageCommande {

  @Min(value = 1, message = "L'année est obligatoire !")
  private int annee;
  @NotBlank(message = "Le numéro de registre est obligatoire !")
  private String numeroRegistre;
  @NotNull(message = "La date du registre est obligatoire !")
  private LocalDate dateRegistre;
  @NotNull(message = "La date du mariage est obligatoire !")
  private LocalDate dateMariage;
  @NotBlank(message = "L'État Civil est obligatoire !")
  private String etatCivil;
  @NotBlank(message = "Le centre d'État Civil est obligatoire !")
  private String centreEtatCivil;
  private String registreN;
  @NotNull(message = "Renseignez les informations du marié !")
  @Valid
  private PersonneExtraitMariage epoux;
  @NotNull(message = "Renseignez les informations de la mariée !")
  @Valid
  private PersonneExtraitMariage epouse;
}
