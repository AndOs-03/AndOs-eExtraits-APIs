package com.andos.eextraits.dto.objetvaleur;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>Regroupe les informations personnelles d'une personne morale sur son extrait de
 * naissance.</p>
 *
 * @author Anderson Ouattara 2025-08-27
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonneExtraitNaissance {

  @NotBlank(message = "Le nom est obligatoire !")
  private String nom;
  @NotBlank(message = "Le prénom est obligatoire !")
  private String prenoms;
  @NotNull(message = "La date de naissance est obligatoire !")
  private LocalDate dateNaissance;
  @NotBlank(message = "Le lieu de naissance est obligatoire !")
  private String lieuNaissance;
  @NotNull(message = "La sexe est obligatoire !")
  private Sexe sexe;
  private MentionsEventuelle mentionsEventuelle;
  @NotNull(message = "Renseignez les informations du père !")
  private ParentExtrait pere;
  @NotNull(message = "Renseignez les informations de la mère !")
  private ParentExtrait mere;
}
