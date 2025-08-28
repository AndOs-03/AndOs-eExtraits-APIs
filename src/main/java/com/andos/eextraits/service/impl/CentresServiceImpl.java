package com.andos.eextraits.service.impl;

import com.andos.eextraits.dto.commande.CreerCentreCommande;
import com.andos.eextraits.dto.commande.ModifierCentreCommande;
import com.andos.eextraits.dto.vm.CentreVM;
import com.andos.eextraits.entity.CentresTable;
import com.andos.eextraits.exception.AndOsEExtraitFunctionnalException;
import com.andos.eextraits.exception.ObjetNonTrouveException;
import com.andos.eextraits.repository.JpaCentresRepository;
import com.andos.eextraits.service.CentresService;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Anderson Ouattara 2025-08-27
 */
@Service
public class CentresServiceImpl implements CentresService {

  private final Logger logger = Logger.getLogger(CentresServiceImpl.class.getName());

  private final JpaCentresRepository jpaCentresRepository;

  public CentresServiceImpl(JpaCentresRepository jpaCentresRepository) {
    this.jpaCentresRepository = jpaCentresRepository;
  }

  @Override
  public void creer(CreerCentreCommande commande) {
    boolean existe = this.jpaCentresRepository.existsByNom(commande.nom());
    if (existe) {
      throw new AndOsEExtraitFunctionnalException("Un centre existe déjà avec ce nom !");
    }

    CentresTable centresTable = new CentresTable();
    centresTable.setNom(commande.nom());
    this.jpaCentresRepository.save(centresTable);
  }

  @Override
  public void modifier(ModifierCentreCommande commande) {
    CentresTable centresTable = this.jpaCentresRepository.findById(commande.id())
        .orElseThrow(() -> new ObjetNonTrouveException("Le centre n'existe pas !"));

    boolean existe = this.jpaCentresRepository.existsByNom(commande.nom());
    if (existe && !Objects.equals(commande.nom(), centresTable.getNom())) {
      throw new AndOsEExtraitFunctionnalException("Un centre existe déjà avec ce nom !");
    }

    centresTable.setNom(commande.nom());
    this.jpaCentresRepository.save(centresTable);
  }

  @Override
  public void supprimer(Long id) {
    try {
      this.jpaCentresRepository.deleteById(id);
    } catch (Exception e) {
      logger.info("###    ERREUR SUPPRESSION CENTRE   \n" + e.getMessage());
      throw new AndOsEExtraitFunctionnalException("Impossible de supprimer ce centre !");
    }
  }

  @Override
  public CentreVM recupererParId(Long id) {
    CentresTable centresTable = this.jpaCentresRepository.findById(id)
        .orElseThrow(() -> new ObjetNonTrouveException("Le centre n'existe pas !"));

    return this.genererCentreVm(centresTable);
  }

  @Override
  public CentreVM recupererParNom(String nom) {
    CentresTable centresTable = this.jpaCentresRepository.findByNom(nom)
        .orElseThrow(() -> new ObjetNonTrouveException("Le centre n'existe pas !"));

    return this.genererCentreVm(centresTable);
  }

  @Override
  public List<CentreVM> lister() {
    List<CentresTable> centresTables = this.jpaCentresRepository.findAll();
    return centresTables.stream().map(this::genererCentreVm).toList();
  }

  private CentreVM genererCentreVm(CentresTable centresTable) {
    return new CentreVM(centresTable.getId(), centresTable.getNom());
  }
}
