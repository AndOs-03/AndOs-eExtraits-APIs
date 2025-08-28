package com.andos.eextraits.mappers;

import com.andos.eextraits.dto.objetvaleur.PersonneExtraitMariage;
import com.andos.eextraits.dto.vm.ExtraitMariageDetailsVM;
import com.andos.eextraits.dto.vm.ExtraitMariageEssentielVM;
import com.andos.eextraits.entity.ExtraitMariagesTable;
import com.andos.eextraits.entity.PersonneExtraitMariageTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Anderson Ouattara 2025-08-28
 */
@Mapper
public abstract class ExtraitsMariageMapper {

  public static final ExtraitsMariageMapper INSTANCE = Mappers.getMapper(
      ExtraitsMariageMapper.class);

  public abstract ExtraitMariageDetailsVM extraitMariagesTableVersExtraitMariageDetailsVM(
      ExtraitMariagesTable extraitMariagesTable);

  public abstract ExtraitMariageEssentielVM extraitMariagesTableVersExtraitMariageEssentielVM(
      ExtraitMariagesTable extraitMariagesTable);

  public abstract PersonneExtraitMariage personneExtraitMariageTableVersPersonneExtraitMariage(
      PersonneExtraitMariageTable personneExtraitMariageTable);

  @Mapping(target = "id", ignore = true)
  public abstract PersonneExtraitMariageTable personneExtraitMariageVersPersonneExtraitMariageTable(
      PersonneExtraitMariage personneExtraitMariage);
}
