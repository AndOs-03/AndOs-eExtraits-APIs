package com.andos.eextraits.service.impl;

import com.andos.eextraits.dto.vm.CentreVM;
import com.andos.eextraits.dto.vm.ExportExtraitDecesVM;
import com.andos.eextraits.dto.vm.ExportExtraitMariageVM;
import com.andos.eextraits.dto.vm.ExportExtraitVM;
import com.andos.eextraits.dto.vm.ExtraitDecesDetailsVM;
import com.andos.eextraits.dto.vm.ExtraitMariageDetailsVM;
import com.andos.eextraits.dto.vm.InstitutionVM;
import com.andos.eextraits.dto.vm.PersonneExtraitMariageExportVM;
import com.andos.eextraits.mappers.ExtraitsDecesMapper;
import com.andos.eextraits.mappers.ExtraitsMariageMapper;
import com.andos.eextraits.mappers.ExtraitsNaissanceMapper;
import com.andos.eextraits.service.CentresService;
import com.andos.eextraits.service.GenererFichierExtraitService;
import com.andos.eextraits.service.InstitutionsService;
import com.andos.eextraits.utils.AbstractJasperRapport;
import com.andos.eextraits.utils.FormaterChiffreService;
import com.andos.eextraits.utils.FormaterDateService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

/**
 * @author Anderson Ouattara 2025-09-22
 */
@Service
public class GenererFichierExtraitServiceImpl extends AbstractJasperRapport implements
    GenererFichierExtraitService {

  private final Logger logger = Logger.getLogger(GenererFichierExtraitServiceImpl.class.getName());

  private final CentresService centresService;
  private final InstitutionsService institutionsService;
  private final ExtraitsDecesMapper extraitsDecesMapper;
  private final ExtraitsMariageMapper extraitsMariageMapper;
  private final ExtraitsNaissanceMapper extraitsNaissanceMapper;

  public GenererFichierExtraitServiceImpl(
      CentresService centresService,
      InstitutionsService institutionsService
  ) {
    this.centresService = centresService;
    this.institutionsService = institutionsService;
    this.extraitsDecesMapper = ExtraitsDecesMapper.INSTANCE;
    this.extraitsMariageMapper = ExtraitsMariageMapper.INSTANCE;
    this.extraitsNaissanceMapper = ExtraitsNaissanceMapper.INSTANCE;
  }

  @Override
  public ByteArrayOutputStream extraitDeces(ExtraitDecesDetailsVM extaitVm, Long institutionId,
      Long centreId) {
    InstitutionVM institutionVm = this.institutionsService.recupererParId(institutionId);
    CentreVM centreVm = this.centresService.recupererParId(centreId);

    ExportExtraitDecesVM rapport = this.extraitsDecesMapper
        .extraitDecesDetailsVmVersExportExtraitDecesVM(extaitVm);

    this.mapperCentreEtInstitution(rapport, centreVm, institutionVm);
    this.assignerSituationMatrimoniale(extaitVm, rapport);
    this.assignerEmbleme(rapport);

    LocalDate dateDeces = extaitVm.dateDeces();
    String dateDecesLettre = this.convertirEnDateLettre(dateDeces);
    rapport.setDateDeces(dateDecesLettre);
    LocalDate dateNaissance = extaitVm.dateNaissance();
    String dateNaissanceLettre = this.convertirEnDateLettre(dateNaissance);
    rapport.setDateNaissance(dateNaissanceLettre);

    this.initialisation("reporting/JrExtraitDeces.jrxml");
    JRBeanCollectionDataSource jrDatasource = this.genererJRDataSource(rapport);
    this.setDataSource(jrDatasource);
    return this.exporter();
  }

  @Override
  public ByteArrayOutputStream extraitMariage(ExtraitMariageDetailsVM extaitVm, Long institutionId,
      Long centreId) {
    InstitutionVM institutionVm = this.institutionsService.recupererParId(institutionId);
    CentreVM centreVm = this.centresService.recupererParId(centreId);

    ExportExtraitMariageVM rapport = this.extraitsMariageMapper
        .extraitMariageDetailsVmVersExportExtraitMariageVM(extaitVm);

    this.mapperCentreEtInstitution(rapport, centreVm, institutionVm);
    this.assignerEmbleme(rapport);

    LocalDate dateMariage = extaitVm.dateMariage();
    String dateMariageLettre = this.convertirEnDateLettre(dateMariage);
    rapport.setDateMariage(dateMariageLettre);

    LocalDate dateNaissanceEpx = extaitVm.epoux().getDateNaissance();
    String dateNaissanceEpxLettre = this.convertirEnDateLettre(dateNaissanceEpx);
    PersonneExtraitMariageExportVM epoux = rapport.getEpoux();
    epoux.setDateNaissance(dateNaissanceEpxLettre);

    LocalDate dateNaissanceEpse = extaitVm.epouse().getDateNaissance();
    String dateNaissanceEpseLettre = this.convertirEnDateLettre(dateNaissanceEpse);
    PersonneExtraitMariageExportVM epouse = rapport.getEpouse();
    epouse.setDateNaissance(dateNaissanceEpseLettre);

    this.initialisation("reporting/JrExtraitMariage.jrxml");
    JRBeanCollectionDataSource jrDatasource = this.genererJRDataSource(rapport);
    this.setDataSource(jrDatasource);
    return this.exporter();
  }

  private void mapperCentreEtInstitution(
      ExportExtraitVM rapport,
      CentreVM centreVm,
      InstitutionVM institutionVm
  ) {
    rapport.setCentre(centreVm.nom());
    rapport.setOfficier(institutionVm.officier());
    rapport.setTitreOfficier(institutionVm.titreOfficier());
  }

  public InputStream recupererEmbleme() throws IOException {
    var classPathResource = new ClassPathResource("images/embleme.jpg");
    return classPathResource.getInputStream();
  }

  private JRBeanCollectionDataSource genererJRDataSource(ExportExtraitDecesVM rapport) {
    return new JRBeanCollectionDataSource(List.of(rapport));
  }

  private JRBeanCollectionDataSource genererJRDataSource(ExportExtraitMariageVM rapport) {
    return new JRBeanCollectionDataSource(List.of(rapport));
  }

  private void assignerEmbleme(ExportExtraitVM rapport) {
    try {
      InputStream embleme = this.recupererEmbleme();
      rapport.setEmbleme(embleme);
    } catch (IOException e) {
      logger.info("Erreur récupération de l'emblème : " + e.getMessage());
    }
  }

  private String convertirEnDateLettre(LocalDate date) {
    String dateLettre = "";
    if (Objects.nonNull(date)) {
      BigDecimal jourMois = BigDecimal.valueOf(date.getDayOfMonth());
      String jour = FormaterChiffreService.convertirValeurEnLettre(jourMois);

      String mois = FormaterDateService.dateVersMoisEnLettre(date);

      BigDecimal anneeChiffre = BigDecimal.valueOf(date.getYear());
      String annee = FormaterChiffreService.convertirValeurEnLettre(anneeChiffre);

      dateLettre = jour + " " + mois + " " + annee;
    }

    return dateLettre;
  }

  private void assignerSituationMatrimoniale(
      ExtraitDecesDetailsVM extaitVm,
      ExportExtraitDecesVM rapport
  ) {
    switch (extaitVm.situationMatrimoniale()) {
      case VEUVE -> rapport.setSituationMatrimoniale("Veuve");
      case CELIBATAIRE -> rapport.setSituationMatrimoniale("Célibataire");
      case MARIEE -> rapport.setSituationMatrimoniale("Mariée");
      case DIVORCEE -> rapport.setSituationMatrimoniale("Divorcée");
    }
  }
}
