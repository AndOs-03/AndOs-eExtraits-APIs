package com.andos.eextraits.service.impl;

import com.andos.eextraits.dto.commande.CreerExtraitNaissanceCommande;
import com.andos.eextraits.dto.commande.ModifierExtraitNaissanceCommande;
import com.andos.eextraits.dto.vm.ExtraitNaissanceDetailsVM;
import com.andos.eextraits.dto.vm.ExtraitNaissanceEssentielVM;
import com.andos.eextraits.entity.CentresTable;
import com.andos.eextraits.entity.ExtraitNaissancesTable;
import com.andos.eextraits.entity.ExtraitNaissancesTable.ExtraitNaissancesBuilder;
import com.andos.eextraits.entity.PersonneExtraitNaissanceTable;
import com.andos.eextraits.exception.AndOsEExtraitFunctionnalException;
import com.andos.eextraits.exception.ObjetNonTrouveException;
import com.andos.eextraits.mappers.ExtraitsNaissanceMapper;
import com.andos.eextraits.repository.JpaCentresRepository;
import com.andos.eextraits.repository.JpaExtraitsNaissancesRepository;
import com.andos.eextraits.service.ExtraitsNaissancesService;
import com.andos.eextraits.utils.FormaterDateService;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Anderson Ouattara 2025-08-29
 */
@Service
public class ExtraitsNaissancesServiceImpl implements ExtraitsNaissancesService {

  private final Logger logger = Logger.getLogger(ExtraitsNaissancesServiceImpl.class.getName());

  private final JpaExtraitsNaissancesRepository jpaExtraitsNaissancesRepository;
  private final JpaCentresRepository jpaCentresRepository;
  private final ExtraitsNaissanceMapper extraitsNaissanceMapper;
  private final FormaterDateService formaterDateService;

  public ExtraitsNaissancesServiceImpl(
      JpaExtraitsNaissancesRepository jpaExtraitsNaissancesRepository,
      JpaCentresRepository jpaCentresRepository
  ) {
    this.jpaExtraitsNaissancesRepository = jpaExtraitsNaissancesRepository;
    this.jpaCentresRepository = jpaCentresRepository;
    this.extraitsNaissanceMapper = ExtraitsNaissanceMapper.INSTANCE;
    this.formaterDateService = new FormaterDateService();
  }

  @Override
  public void creer(CreerExtraitNaissanceCommande commande) {
    this.verifierCentre(commande);
    String nouveauRegistre = this.genererNouveauRegistre(commande);
    boolean existe = this.jpaExtraitsNaissancesRepository
        .existsByRegistreAndCentreId(nouveauRegistre, commande.getCentreId());
    if (existe) {
      throw new AndOsEExtraitFunctionnalException("Un extrait de naissance existe déjà avec ce "
          + "registre !");
    }

    ExtraitNaissancesTable extraitTable = this.genererExtraitNaissancesTable(commande,
        nouveauRegistre, null);
    this.jpaExtraitsNaissancesRepository.save(extraitTable);
    logger.info("Création d'un extrait de naissance REGISTRE : " + nouveauRegistre);
  }

  @Override
  public void modifier(ModifierExtraitNaissanceCommande commande) {
    ExtraitNaissancesTable extraitTable = jpaExtraitsNaissancesRepository.findById(commande.getId())
        .orElseThrow(() -> new ObjetNonTrouveException("L'extrait de naissance n'existe pas !"));
    this.verifierCentre(commande);

    String nouveauRegistre = this.genererNouveauRegistre(commande);
    boolean existe = this.jpaExtraitsNaissancesRepository
        .existsByRegistreAndCentreId(nouveauRegistre, commande.getCentreId());
    if (existe && !Objects.equals(commande.getId(), extraitTable.getId())) {
      throw new AndOsEExtraitFunctionnalException("Un extrait de naissance existe déjà avec ce "
          + "registre !");
    }

    extraitTable = this.genererExtraitNaissancesTable(commande, nouveauRegistre, extraitTable);
    extraitTable.setId(commande.getId());
    this.jpaExtraitsNaissancesRepository.save(extraitTable);
    logger.info("Modification d'un extrait de naissance REGISTRE : " + nouveauRegistre);
  }

  @Override
  public void supprimer(Long id) {
    try {
      this.jpaExtraitsNaissancesRepository.deleteById(id);
      logger.info("Suppression d'un extrait de naissance ID : " + id);
    } catch (Exception e) {
      logger.info("###    ERREUR SUPPRESSION EXTRAIT NAISSANCE   \n" + e.getMessage());
      throw new AndOsEExtraitFunctionnalException("Impossible de supprimer cet extrait de "
          + "naissance !");
    }
  }

  @Override
  public ExtraitNaissanceDetailsVM recupererParId(Long id) {
    ExtraitNaissancesTable extraitTable = this.jpaExtraitsNaissancesRepository.findById(id)
        .orElseThrow(() -> new ObjetNonTrouveException("L'extrait de naissance n'existe pas !"));
    return extraitsNaissanceMapper.extraitNaissanceTableVersExtraitNaissanceDetailsVM(extraitTable);
  }

  @Override
  public ExtraitNaissanceEssentielVM recupererParIdEssentiel(Long id) {
    ExtraitNaissancesTable extraitTable = this.jpaExtraitsNaissancesRepository.findById(id)
        .orElseThrow(() -> new ObjetNonTrouveException("L'extrait de naissance n'existe pas !"));
    return extraitsNaissanceMapper.extraitNaissanceTableVersExtraitNaissanceEssentielVM(
        extraitTable);
  }

  @Override
  public ExtraitNaissanceDetailsVM recupererParRegistre(String registre, Long centreId) {
    if (Objects.isNull(centreId)) {
      throw new AndOsEExtraitFunctionnalException("Veillez renseigner le centre !");
    }

    ExtraitNaissancesTable extraitTable = this.jpaExtraitsNaissancesRepository
        .findByRegistreAndCentreId(registre, centreId)
        .orElseThrow(() -> new ObjetNonTrouveException("L'extrait de naissance n'existe pas !"));
    return extraitsNaissanceMapper.extraitNaissanceTableVersExtraitNaissanceDetailsVM(extraitTable);
  }

  @Override
  public List<ExtraitNaissanceEssentielVM> lister(Long centreId) {
    if (Objects.isNull(centreId)) {
      throw new AndOsEExtraitFunctionnalException("Veillez renseigner le centre !");
    }

    List<ExtraitNaissancesTable> extraitTables = this.jpaExtraitsNaissancesRepository
        .findByCentreId(centreId);
    return extraitTables.stream()
        .map(this.extraitsNaissanceMapper::extraitNaissanceTableVersExtraitNaissanceEssentielVM)
        .toList();
  }

  private ExtraitNaissancesTable genererExtraitNaissancesTable(
      CreerExtraitNaissanceCommande commande,
      String nouveauRegistre,
      ExtraitNaissancesTable extraitTable
  ) {
    PersonneExtraitNaissanceTable personne = this.extraitsNaissanceMapper
        .personneExtraitNaissanceVersPersonneExtraitNaissanceTable(commande.getPersonne());
    if (Objects.nonNull(extraitTable) && Objects.nonNull(extraitTable.getPersonne())) {
      personne.setId(extraitTable.getPersonne().getId());
    }

    ExtraitNaissancesTable build = new ExtraitNaissancesBuilder()
        .annee(commande.getAnnee())
        .numeroRegistre(commande.getNumeroRegistre())
        .dateRegistre(commande.getDateRegistre())
        .registre(nouveauRegistre)
        .numeroJugementSupletif(commande.getNumeroJugementSupletif())
        .dateJugementSupletif(commande.getDateJugementSupletif())
        .tribunalJugementSupletif(commande.getTribunalJugementSupletif())
        .extraitTypeTPI(commande.isExtraitTypeTPI())
        .etatCivil(commande.getEtatCivil())
        .centreEtatCivil(commande.getCentreEtatCivil())
        .registreN(commande.getRegistreN())
        .nouveauModel(commande.isNouveauModel())
        .personne(personne)
        .build();

    if (!commande.isExtraitTypeTPI()) {
      build.setNumeroJugementSupletif(null);
      build.setDateJugementSupletif(null);
      build.setTribunalJugementSupletif(null);
    }

    if (Objects.nonNull(commande.getCentreId())) {
      CentresTable centresTable = new CentresTable();
      centresTable.setId(commande.getCentreId());
      build.setCentre(centresTable);
    } else if (Objects.nonNull(extraitTable) && Objects.nonNull(extraitTable.getCentre())) {
      build.setCentre(extraitTable.getCentre());
    }

    return build;
  }

  private String genererNouveauRegistre(CreerExtraitNaissanceCommande commande) {
    String dateRegistreFrancais = this.formaterDateService
        .formatJourMoisAnnee(commande.getDateRegistre());
    return commande.getNumeroRegistre() + " du " + dateRegistreFrancais;
  }

  private void verifierCentre(CreerExtraitNaissanceCommande commande) {
    if (Objects.nonNull(commande.getCentreId())) {
      boolean centreExiste = this.jpaCentresRepository.existsById(commande.getCentreId());
      if (!centreExiste) {
        throw new ObjetNonTrouveException("Le centre n'existe pas");
      }
    }
  }
}
