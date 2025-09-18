package com.andos.eextraits.dto.objetvaleur;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anderson Ouattara 2025-08-29
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MentionsEventuelle {

  private Boolean mariage = false;
  private LocalDate dateMariage;
  private String lieuMariage;
  private String epouOuEpouse;
  private Boolean divorce = false;
  private LocalDate dateDivorce;
  private Boolean decede = false;
  private LocalDate dateDeces;
  private String lieuDeces;
}
