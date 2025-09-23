package com.andos.eextraits.service.impl;

import com.andos.eextraits.dto.vm.CentreVM;
import com.andos.eextraits.dto.vm.ExportExtraitDecesVM;
import com.andos.eextraits.dto.vm.ExtraitDecesDetailsVM;
import com.andos.eextraits.dto.vm.InstitutionVM;
import com.andos.eextraits.mappers.ExtraitsDecesMapper;
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

  public GenererFichierExtraitServiceImpl(
      CentresService centresService,
      InstitutionsService institutionsService
  ) {
    this.centresService = centresService;
    this.institutionsService = institutionsService;
    this.extraitsDecesMapper = ExtraitsDecesMapper.INSTANCE;
  }

  @Override
  public ByteArrayOutputStream extraitDeces(ExtraitDecesDetailsVM extaitVm, Long institutionId,
      Long centreId) {
    InstitutionVM institutionVm = this.institutionsService.recupererParId(institutionId);
    CentreVM centreVm = this.centresService.recupererParId(centreId);

    ExportExtraitDecesVM rapport = this.extraitsDecesMapper
        .extraitDecesDetailsVmVersExportExtraitDecesVM(extaitVm);
    rapport.setCentre(centreVm.nom());
    rapport.setOfficier(institutionVm.officier());
    rapport.setTitreOfficier(institutionVm.titreOfficier());

    this.assignerSituationMatrimoniale(extaitVm, rapport);

    LocalDate dateDeces = extaitVm.dateDeces();
    String dateDecesLettre = this.convertirEnDateLettre(dateDeces);
    rapport.setDateDeces(dateDecesLettre);
    LocalDate dateNaissance = extaitVm.dateNaissance();
    String dateNaissanceLettre = this.convertirEnDateLettre(dateNaissance);
    rapport.setDateNaissance(dateNaissanceLettre);

    this.assignerEmbleme(rapport);

    this.initialisation("reporting/JrExtraitDeces.jrxml");
    JRBeanCollectionDataSource jrDatasource = this.genererJRDataSource(rapport);
    this.setDataSource(jrDatasource);
    return this.exporter();
  }

  @Override
  public InputStream recupererEmbleme() throws IOException {
    var classPathResource = new ClassPathResource("images/embleme.jpg");
    return classPathResource.getInputStream();
  }

  private JRBeanCollectionDataSource genererJRDataSource(ExportExtraitDecesVM rapport) {
    return new JRBeanCollectionDataSource(List.of(rapport));
  }

  private void assignerEmbleme(ExportExtraitDecesVM rapport) {
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
