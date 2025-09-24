package com.andos.eextraits.mappers;

import com.andos.eextraits.dto.objetvaleur.MentionsEventuelle;
import com.andos.eextraits.dto.objetvaleur.PersonneExtraitNaissance;
import com.andos.eextraits.dto.vm.ExportExtraitNaissanceVM;
import com.andos.eextraits.dto.vm.ExtraitNaissanceDetailsVM;
import com.andos.eextraits.dto.vm.ExtraitNaissanceEssentielVM;
import com.andos.eextraits.dto.vm.MentionsEventuelleExportVM;
import com.andos.eextraits.dto.vm.PersonneExtraitNaissanceExportVM;
import com.andos.eextraits.entity.ExtraitNaissancesTable;
import com.andos.eextraits.entity.PersonneExtraitNaissanceTable;
import java.util.Objects;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Anderson Ouattara 2025-08-29
 */
@Mapper
public abstract class ExtraitsNaissanceMapper {

  public static final ExtraitsNaissanceMapper INSTANCE = Mappers.getMapper(
      ExtraitsNaissanceMapper.class);

  public abstract ExtraitNaissanceDetailsVM extraitNaissanceTableVersExtraitNaissanceDetailsVM(
      ExtraitNaissancesTable extraitNaissanceTable);

  @Mapping(target = "prenoms", source = "personne.prenoms")
  @Mapping(target = "nom", source = "personne.nom")
  @Mapping(target = "lieuNaissance", source = "personne.lieuNaissance")
  @Mapping(target = "dateNaissance", source = "personne.dateNaissance")
  public abstract ExtraitNaissanceEssentielVM extraitNaissanceTableVersExtraitNaissanceEssentielVM(
      ExtraitNaissancesTable extraitNaissanceTable);

  @Mapping(target = "mentionsEventuelle", expression = "java(mapperMentionEventuelle(personneExtraitNaissance.getMentionsEventuelle()))")
  public abstract PersonneExtraitNaissance personneExtraitNaissanceTableVersPersonneExtraitNaissance(
      PersonneExtraitNaissanceTable personneExtraitNaissanceTable);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "mentionsEventuelle", expression = "java(mapperMentionEventuelle(personneExtraitNaissance.getMentionsEventuelle()))")
  public abstract PersonneExtraitNaissanceTable personneExtraitNaissanceVersPersonneExtraitNaissanceTable(
      PersonneExtraitNaissance personneExtraitNaissance);

  @Mapping(target = "titreOfficier", ignore = true)
  @Mapping(target = "officier", ignore = true)
  @Mapping(target = "centre", ignore = true)
  @Mapping(target = "embleme", ignore = true)
  public abstract ExportExtraitNaissanceVM extraitNaissanceDetailsVmVersExportExtraitNaissanceVM(
      ExtraitNaissanceDetailsVM extraitNaissanceDetailsVm);

  @Mapping(target = "dateMariage", ignore = true)
  @Mapping(target = "dateDivorce", ignore = true)
  @Mapping(target = "dateDeces", ignore = true)
  public abstract MentionsEventuelleExportVM mentionsEventuelleVersMentionsEventuelleExportVM(
      MentionsEventuelle mentionsEventuelle);

  @Mapping(target = "dateNaissance", ignore = true)
  @Mapping(target = "pere", source = "pere.nomPrenoms")
  @Mapping(target = "mere", source = "mere.nomPrenoms")
  public abstract PersonneExtraitNaissanceExportVM personneExtraitNaissanceVersPersonneExtraitNaissanceExportVM(
      PersonneExtraitNaissance personneExtraitNaissance);

  public MentionsEventuelle mapperMentionEventuelle(MentionsEventuelle mentionsEventuelle) {
    if (Objects.isNull(mentionsEventuelle)) {
      return null;
    }

    if (!mentionsEventuelle.getMariage()) {
      mentionsEventuelle.setDateMariage(null);
      mentionsEventuelle.setLieuMariage(null);
      mentionsEventuelle.setEpouOuEpouse(null);
    }

    if (!mentionsEventuelle.getDivorce()) {
      mentionsEventuelle.setDateDivorce(null);
    }

    if (!mentionsEventuelle.getDecede()) {
      mentionsEventuelle.setDateDeces(null);
      mentionsEventuelle.setLieuDeces(null);
    }

    return mentionsEventuelle;
  }
}
