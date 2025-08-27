package com.andos.eextraits.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>Gestion centralisée des exception de l'application.</p>
 *
 * @author Anderson Ouattara 2025-08-27
 */
@RestControllerAdvice
public class TransformationException {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, List<String>>> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e
  ) {
    List<String> erreurs = e.getBindingResult().getFieldErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .toList();

    Map<String, List<String>> errorResponse = new HashMap<>();
    errorResponse.put("erreurs", erreurs);
    return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ObjetNonTrouveException.class)
  public ResponseEntity<ExceptionReponseApi> handleObjetNonTrouveException(
      ObjetNonTrouveException e) {
    ExceptionReponseApi reponse = new ExceptionReponseApi(e.getMessage(), 404);
    return new ResponseEntity<>(reponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AndOsEExtraitFunctionnalException.class)
  public ResponseEntity<ExceptionReponseApi> handleAndOsEExtraitFunctionnalException(
      AndOsEExtraitFunctionnalException e) {
    ExceptionReponseApi reponse = new ExceptionReponseApi(e.getMessage(), 500);
    return new ResponseEntity<>(reponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
