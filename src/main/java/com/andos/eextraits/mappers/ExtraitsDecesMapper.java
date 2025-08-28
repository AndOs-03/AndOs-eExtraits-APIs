package com.andos.eextraits.mappers;

import com.andos.eextraits.dto.vm.ExtraitDecesDetailsVM;
import com.andos.eextraits.dto.vm.ExtraitDecesEssentielVM;
import com.andos.eextraits.entity.ExtraitDecesTable;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Anderson Ouattara 2025-08-28
 */
@Mapper
public abstract class ExtraitsDecesMapper {

  public static final ExtraitsDecesMapper INSTANCE = Mappers.getMapper(ExtraitsDecesMapper.class);

  public abstract ExtraitDecesDetailsVM extraitDecesTableVersExtraitDecesDetailsVM(
      ExtraitDecesTable extraitDecesTable);

  public abstract ExtraitDecesEssentielVM extraitDecesTableVersExtraitDecesEssentielVM(
      ExtraitDecesTable extraitDecesTable);
}
