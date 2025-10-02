package com.andos.eextraits.rest;

import com.andos.eextraits.dto.vm.TableauBordExtraitsVM;
import com.andos.eextraits.service.TableauBordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Anderson Ouattara 2025-10-02
 */
@RestController
@RequestMapping("/api/extraits/tableau-bord")
public class TableauBordRessource {

  private final TableauBordService tableauBordService;

  public TableauBordRessource(TableauBordService tableauBordService) {
    this.tableauBordService = tableauBordService;
  }

  @GetMapping("/{centreId}/tableau-bord-extraits")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<TableauBordExtraitsVM> tableauBordExtraits(
      @PathVariable("centreId") Long centreId
  ) {
    TableauBordExtraitsVM vm = this.tableauBordService.tableauBordExtraits(centreId);
    return new ResponseEntity<>(vm, HttpStatus.OK);
  }
}
