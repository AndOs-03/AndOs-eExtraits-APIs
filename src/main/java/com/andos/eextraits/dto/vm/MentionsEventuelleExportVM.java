package com.andos.eextraits.dto.vm;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anderson Ouattara 2025-09-24
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MentionsEventuelleExportVM {

  private Boolean mariage = false;
  private String dateMariage;
  private String lieuMariage;
  private String epouOuEpouse;
  private Boolean divorce = false;
  private String dateDivorce;
  private Boolean decede = false;
  private String dateDeces;
  private String lieuDeces;
}
