package com.andos.eextraits.rest;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author Anderson Ouattara 2025-08-27
 */
@RestController
@RequestMapping("/test")
public class TestRessource {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void creer(@Valid @RequestBody List<String> test) {
  }
}
