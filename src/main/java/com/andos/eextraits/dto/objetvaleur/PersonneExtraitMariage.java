package com.andos.eextraits.dto.objetvaleur;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>Regroupe les informations personnelles d'une personne morale sur son extrait de mariage.</p>
 *
 * @author Anderson Ouattara 2025-08-27
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonneExtraitMariage {

  @NotBlank(message = "Le nom est obligatoire !")
  private String nomPrenoms;
  @NotNull(message = "La date de naissance est obligatoire !")
  private LocalDate dateNaissance;
  @NotBlank(message = "Le lieu de naissance est obligatoire !")
  private String lieuNaissance;
  private String domicile;
  private ParentExtrait pere;
  private ParentExtrait mere;
}
