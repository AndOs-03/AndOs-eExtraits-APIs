package com.andos.eextraits.dto.vm;

import java.time.LocalDate;
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
public class ExportExtraitNaissanceVM extends ExportExtraitVM {

  private int annee;
  private String numeroJugementSupletif;
  private LocalDate dateJugementSupletif;
  private String tribunalJugementSupletif;
  private boolean extraitTypeTPI;
  private boolean nouveauModel;
  private PersonneExtraitNaissanceExportVM personne;
}
