package com.andos.eextraits.dto.vm;

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
public class ExportExtraitMariageVM extends ExportExtraitVM {

  private int annee;
  private String dateMariage;
  private PersonneExtraitMariageExportVM epoux;
  private PersonneExtraitMariageExportVM epouse;
}
