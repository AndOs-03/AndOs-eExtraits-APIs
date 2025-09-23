package com.andos.eextraits.mappers;

import com.andos.eextraits.dto.vm.ExportExtraitDecesVM;
import com.andos.eextraits.dto.vm.ExtraitDecesDetailsVM;
import com.andos.eextraits.dto.vm.ExtraitDecesEssentielVM;
import com.andos.eextraits.entity.ExtraitDecesTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

  @Mapping(target = "titreOfficier", ignore = true)
  @Mapping(target = "officier", ignore = true)
  @Mapping(target = "centre", ignore = true)
  @Mapping(target = "embleme", ignore = true)
  @Mapping(target = "situationMatrimoniale", ignore = true)
  @Mapping(target = "dateNaissance", ignore = true)
  @Mapping(target = "dateDeces", ignore = true)
  @Mapping(target = "pere", source = "pere.nomPrenoms")
  @Mapping(target = "mere", source = "mere.nomPrenoms")
  public abstract ExportExtraitDecesVM extraitDecesDetailsVmVersExportExtraitDecesVM(
      ExtraitDecesDetailsVM extraitDecesDetailsVm);
}
