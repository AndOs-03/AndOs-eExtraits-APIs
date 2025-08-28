package com.andos.eextraits.service.impl;

import com.andos.eextraits.dto.commande.CreerExtraitMariageCommande;
import com.andos.eextraits.dto.commande.ModifierExtraitMariageCommande;
import com.andos.eextraits.dto.vm.ExtraitMariageDetailsVM;
import com.andos.eextraits.dto.vm.ExtraitMariageEssentielVM;
import com.andos.eextraits.entity.ExtraitMariagesTable;
import com.andos.eextraits.entity.PersonneExtraitMariageTable;
import com.andos.eextraits.exception.AndOsEExtraitFunctionnalException;
import com.andos.eextraits.exception.ObjetNonTrouveException;
import com.andos.eextraits.mappers.ExtraitsMariageMapper;
import com.andos.eextraits.repository.JpaExtraitsMariagesRepository;
import com.andos.eextraits.repository.JpaPersonneExtraitMariageRepository;
import com.andos.eextraits.service.ExtraitsMariagesService;
import com.andos.eextraits.utils.FormaterDateService;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Anderson Ouattara 2025-08-28
 */
@Service
public class ExtraitsMariagesServiceImpl implements ExtraitsMariagesService {

  private final Logger logger = Logger.getLogger(ExtraitsMariagesServiceImpl.class.getName());

  private final JpaExtraitsMariagesRepository jpaExtraitsMariagesRepository;
  private final JpaPersonneExtraitMariageRepository jpaPersonneExtraitMariageRepository;
  private final ExtraitsMariageMapper extraitsMariageMapper;
  private final FormaterDateService formaterDateService;

  public ExtraitsMariagesServiceImpl(
      JpaExtraitsMariagesRepository jpaExtraitsMariagesRepository,
      JpaPersonneExtraitMariageRepository jpaPersonneExtraitMariageRepository
  ) {
    this.jpaExtraitsMariagesRepository = jpaExtraitsMariagesRepository;
    this.jpaPersonneExtraitMariageRepository = jpaPersonneExtraitMariageRepository;
    this.extraitsMariageMapper = ExtraitsMariageMapper.INSTANCE;
    this.formaterDateService = new FormaterDateService();
  }

  @Override
  public void creer(CreerExtraitMariageCommande commande) {
    String nouveauRegistre = this.genererNouveauRegistre(commande);
    boolean existe = this.jpaExtraitsMariagesRepository.existsByRegistre(nouveauRegistre);
    if (existe) {
      throw new AndOsEExtraitFunctionnalException("Un extrait de mariage existe déjà avec ce "
          + "registre !");
    }

    this.verifierLesDatesNaissance(commande);
    ExtraitMariagesTable extraitTable = this.genererExtraitMariagesTable(commande, nouveauRegistre,
        null);
    this.jpaExtraitsMariagesRepository.save(extraitTable);
  }

  @Override
  public void modifier(ModifierExtraitMariageCommande commande) {
    ExtraitMariagesTable extraitTable = jpaExtraitsMariagesRepository.findById(commande.getId())
        .orElseThrow(() -> new ObjetNonTrouveException("L'extrait de mariage n'existe pas !"));

    String nouveauRegistre = this.genererNouveauRegistre(commande);
    boolean existe = this.jpaExtraitsMariagesRepository.existsByRegistre(nouveauRegistre);
    if (existe && !Objects.equals(commande.getId(), extraitTable.getId())) {
      throw new AndOsEExtraitFunctionnalException("Un extrait de mariage existe déjà avec ce "
          + "registre !");
    }

    this.verifierLesDatesNaissance(commande);
    extraitTable = this.genererExtraitMariagesTable(commande, nouveauRegistre, extraitTable);
    extraitTable.setId(commande.getId());
    this.jpaExtraitsMariagesRepository.save(extraitTable);
  }

  @Override
  public void supprimer(Long id) {
    try {
      this.jpaExtraitsMariagesRepository.deleteById(id);
    } catch (Exception e) {
      logger.info("###    ERREUR SUPPRESSION EXTRAIT MARIAGE   \n" + e.getMessage());
      throw new AndOsEExtraitFunctionnalException("Impossible de supprimer cet extrait de mariage "
          + "!");
    }
  }

  @Override
  public ExtraitMariageDetailsVM recupererParId(Long id) {
    ExtraitMariagesTable extraitTable = this.jpaExtraitsMariagesRepository.findById(id)
        .orElseThrow(() -> new ObjetNonTrouveException("L'extrait de mariage n'existe pas !"));
    return this.extraitsMariageMapper.extraitMariagesTableVersExtraitMariageDetailsVM(extraitTable);
  }

  @Override
  public ExtraitMariageDetailsVM recupererParRegistre(String registre) {
    ExtraitMariagesTable extraitTable = this.jpaExtraitsMariagesRepository.findByRegistre(registre)
        .orElseThrow(() -> new ObjetNonTrouveException("L'extrait de mariage n'existe pas !"));
    return this.extraitsMariageMapper.extraitMariagesTableVersExtraitMariageDetailsVM(extraitTable);
  }

  @Override
  public List<ExtraitMariageEssentielVM> lister() {
    List<ExtraitMariagesTable> extraitDecesTables = this.jpaExtraitsMariagesRepository.findAll();
    return extraitDecesTables.stream()
        .map(this.extraitsMariageMapper::extraitMariagesTableVersExtraitMariageEssentielVM)
        .toList();
  }

  private ExtraitMariagesTable genererExtraitMariagesTable(
      CreerExtraitMariageCommande commande,
      String nouveauRegistre,
      ExtraitMariagesTable extraitTable
  ) {
    PersonneExtraitMariageTable epoux = this.extraitsMariageMapper
        .personneExtraitMariageVersPersonneExtraitMariageTable(commande.getEpoux());
    PersonneExtraitMariageTable epouse = this.extraitsMariageMapper
        .personneExtraitMariageVersPersonneExtraitMariageTable(commande.getEpouse());
    if (Objects.nonNull(extraitTable)) {
      if (Objects.nonNull(extraitTable.getEpoux())) {
        epoux.setId(extraitTable.getEpoux().getId());
      }
      if (Objects.nonNull(extraitTable.getEpouse())) {
        epouse.setId(extraitTable.getEpouse().getId());
      }
    }

    return new ExtraitMariagesTable.ExtraitMariagesBuilder()
        .annee(commande.getAnnee())
        .numeroRegistre(commande.getNumeroRegistre())
        .dateRegistre(commande.getDateRegistre())
        .registre(nouveauRegistre)
        .dateMariage(commande.getDateMariage())
        .etatCivil(commande.getEtatCivil())
        .centreEtatCivil(commande.getCentreEtatCivil())
        .registreN(commande.getRegistreN())
        .epoux(epoux)
        .epouse(epouse)
        .build();
  }

  private String genererNouveauRegistre(CreerExtraitMariageCommande commande) {
    String dateRegistreFrancais = this.formaterDateService
        .formatJourMoisAnnee(commande.getDateRegistre());
    return commande.getNumeroRegistre() + " du " + dateRegistreFrancais;
  }

  private void verifierLesDatesNaissance(CreerExtraitMariageCommande commande) {
    if (commande.getEpoux().getDateNaissance().isAfter(commande.getDateMariage())) {
      throw new AndOsEExtraitFunctionnalException("La date de naissance du marié doit être avant "
          + "la date de mariage !");
    }

    if (commande.getEpouse().getDateNaissance().isAfter(commande.getDateMariage())) {
      throw new AndOsEExtraitFunctionnalException("La date de naissance de la marié doit être "
          + "avant la date de mariage !");
    }
  }
}
