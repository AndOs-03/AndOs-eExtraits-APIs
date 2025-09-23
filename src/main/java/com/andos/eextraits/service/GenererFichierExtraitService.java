package com.andos.eextraits.service;

import com.andos.eextraits.dto.vm.ExtraitDecesDetailsVM;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Anderson Ouattara 2025-09-22
 */
public interface GenererFichierExtraitService {

  ByteArrayOutputStream extraitDeces(ExtraitDecesDetailsVM extaitVm, Long institutionId,
      Long centreId);

  InputStream recupererEmbleme() throws IOException;
}
