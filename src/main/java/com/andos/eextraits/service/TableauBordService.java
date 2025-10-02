package com.andos.eextraits.service;

import com.andos.eextraits.dto.vm.TableauBordExtraitsVM;

/**
 * @author Anderson Ouattara 2025-10-02
 */
public interface TableauBordService {

  TableauBordExtraitsVM tableauBordExtraits(Long centreId);
}
