package com.andos.eextraits.service.impl;

import com.andos.eextraits.dto.commande.CreerExtraitDecesCommande;
import com.andos.eextraits.dto.commande.ModifierExtraitDecesCommande;
import com.andos.eextraits.dto.vm.ExtraitDecesDetailsVM;
import com.andos.eextraits.dto.vm.ExtraitDecesEssentielVM;
import com.andos.eextraits.entity.ExtraitDecesTable;
import com.andos.eextraits.exception.AndOsEExtraitFunctionnalException;
import com.andos.eextraits.exception.ObjetNonTrouveException;
import com.andos.eextraits.mappers.ExtraitsDecesMapper;
import com.andos.eextraits.repository.JpaExtraitsDecesRepository;
import com.andos.eextraits.service.ExtraitsDecesService;
import com.andos.eextraits.utils.FormaterDateService;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Anderson Ouattara 2025-08-28
 */
@Service
public class ExtraitsDecesServiceImpl implements ExtraitsDecesService {

  private final Logger logger = Logger.getLogger(ExtraitsDecesServiceImpl.class.getName());

  private final JpaExtraitsDecesRepository jpaExtraitsDecesRepository;
  private final ExtraitsDecesMapper extraitsDecesMapper;
  private final FormaterDateService formaterDateService;

  public ExtraitsDecesServiceImpl(JpaExtraitsDecesRepository jpaExtraitsDecesRepository) {
    this.jpaExtraitsDecesRepository = jpaExtraitsDecesRepository;
    this.extraitsDecesMapper = ExtraitsDecesMapper.INSTANCE;
    this.formaterDateService = new FormaterDateService();
  }

  @Override
  public void creer(CreerExtraitDecesCommande commande) {
    String nouveauRegistre = this.genererNouveauRegistre(commande);
    boolean existe = this.jpaExtraitsDecesRepository.existsByRegistre(nouveauRegistre);
    if (existe) {
      throw new AndOsEExtraitFunctionnalException("Un extrait de décès existe déjà avec ce "
          + "registre !");
    }

    if (commande.getDateNaissance().isAfter(commande.getDateDeces())) {
      throw new AndOsEExtraitFunctionnalException("La date de naissance doit être avant la date "
          + "de décès !");
    }

    ExtraitDecesTable extraitDecesTable = this.genererExtraitDecesTable(commande, nouveauRegistre);
    this.jpaExtraitsDecesRepository.save(extraitDecesTable);
  }

  @Override
  public void modifier(ModifierExtraitDecesCommande commande) {
    ExtraitDecesTable extraitDecesTable = this.jpaExtraitsDecesRepository.findById(commande.getId())
        .orElseThrow(() -> new ObjetNonTrouveException("L'extrait de décès n'existe pas !"));

    String nouveauRegistre = this.genererNouveauRegistre(commande);
    boolean existe = this.jpaExtraitsDecesRepository.existsByRegistre(nouveauRegistre);
    if (existe && !Objects.equals(commande.getId(), extraitDecesTable.getId())) {
      throw new AndOsEExtraitFunctionnalException("Un extrait de décès existe déjà avec ce "
          + "registre !");
    }

    if (commande.getDateNaissance().isAfter(commande.getDateDeces())) {
      throw new AndOsEExtraitFunctionnalException("La date de naissance doit être avant la date "
          + "de décès !");
    }

    extraitDecesTable = this.genererExtraitDecesTable(commande, nouveauRegistre);
    extraitDecesTable.setId(commande.getId());
    this.jpaExtraitsDecesRepository.save(extraitDecesTable);
  }

  @Override
  public void supprimer(Long id) {
    try {
      this.jpaExtraitsDecesRepository.deleteById(id);
    } catch (Exception e) {
      logger.info("###    ERREUR SUPPRESSION EXTRAIT DÉCES   \n" + e.getMessage());
      throw new AndOsEExtraitFunctionnalException("Impossible de supprimer cet extrait de décès !");
    }
  }

  @Override
  public ExtraitDecesDetailsVM recupererParId(Long id) {
    ExtraitDecesTable extraitDecesTable = this.jpaExtraitsDecesRepository.findById(id)
        .orElseThrow(() -> new ObjetNonTrouveException("L'extrait de décès n'existe pas !"));
    return this.extraitsDecesMapper.extraitDecesTableVersExtraitDecesDetailsVM(extraitDecesTable);
  }

  @Override
  public ExtraitDecesDetailsVM recupererParRegistre(String registre) {
    ExtraitDecesTable extraitDecesTable = this.jpaExtraitsDecesRepository.findByRegistre(registre)
        .orElseThrow(() -> new ObjetNonTrouveException("Aucun extrait de décès trouvé avec ce "
            + "registre !"));
    return this.extraitsDecesMapper.extraitDecesTableVersExtraitDecesDetailsVM(extraitDecesTable);
  }

  @Override
  public List<ExtraitDecesEssentielVM> lister() {
    List<ExtraitDecesTable> extraitDecesTables = this.jpaExtraitsDecesRepository.findAll();
    return extraitDecesTables.stream()
        .map(this.extraitsDecesMapper::extraitDecesTableVersExtraitDecesEssentielVM)
        .toList();
  }

  private ExtraitDecesTable genererExtraitDecesTable(
      CreerExtraitDecesCommande commande,
      String nouveauRegistre
  ) {
    return new ExtraitDecesTable.ExtraitDecesBuilder()
        .annee(commande.getAnnee())
        .numeroRegistre(commande.getNumeroRegistre())
        .dateRegistre(commande.getDateRegistre())
        .registre(nouveauRegistre)
        .dateDeces(commande.getDateDeces())
        .lieuDeces(commande.getLieuDeces())
        .nom(commande.getNom())
        .prenoms(commande.getPrenoms())
        .nationalite(commande.getNationalite())
        .profession(commande.getProfession())
        .domicile(commande.getDomicile())
        .dateNaissance(commande.getDateNaissance())
        .lieuNaissance(commande.getLieuNaissance())
        .etatCivil(commande.getEtatCivil())
        .centreEtatCivil(commande.getCentreEtatCivil())
        .registreN(commande.getRegistreN())
        .situationMatrimoniale(commande.getSituationMatrimoniale())
        .pere(commande.getPere())
        .mere(commande.getMere())
        .build();
  }

  private String genererNouveauRegistre(CreerExtraitDecesCommande commande) {
    String dateRegistreFrancais = this.formaterDateService
        .formatJourMoisAnnee(commande.getDateRegistre());
    return commande.getNumeroRegistre() + " du " + dateRegistreFrancais;
  }
}
