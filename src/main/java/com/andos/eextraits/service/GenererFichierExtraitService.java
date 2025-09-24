package com.andos.eextraits.service;

import com.andos.eextraits.dto.vm.ExtraitDecesDetailsVM;
import com.andos.eextraits.dto.vm.ExtraitMariageDetailsVM;
import java.io.ByteArrayOutputStream;

/**
 * @author Anderson Ouattara 2025-09-22
 */
public interface GenererFichierExtraitService {

  ByteArrayOutputStream extraitDeces(ExtraitDecesDetailsVM extaitVm, Long institutionId,
      Long centreId);

  ByteArrayOutputStream extraitMariage(ExtraitMariageDetailsVM extaitVm, Long institutionId,
      Long centreId);
}
