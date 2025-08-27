package com.andos.eextraits.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>Classe d'exception des API.</p>
 *
 * @author Anderson Ouattara 2025-08-27
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionReponseApi {

  private String message;
  private int statut;
}
