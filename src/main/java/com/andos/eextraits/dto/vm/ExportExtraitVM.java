package com.andos.eextraits.dto.vm;

import java.io.InputStream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anderson Ouattara 2025-09-22
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExportExtraitVM {

  private InputStream embleme;
  private String centre;
  private String officier;
  private String titreOfficier;
  private String etatCivil;
  private String centreEtatCivil;
  private String registre;
  private String registreN;
}
