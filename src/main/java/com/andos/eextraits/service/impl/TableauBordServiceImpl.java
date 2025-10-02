package com.andos.eextraits.service.impl;

import com.andos.eextraits.dto.vm.TableauBordExtraitsVM;
import com.andos.eextraits.entity.ExtraitDecesTable;
import com.andos.eextraits.entity.ExtraitMariagesTable;
import com.andos.eextraits.entity.ExtraitNaissancesTable;
import com.andos.eextraits.repository.JpaExtraitsDecesRepository;
import com.andos.eextraits.repository.JpaExtraitsMariagesRepository;
import com.andos.eextraits.repository.JpaExtraitsNaissancesRepository;
import com.andos.eextraits.service.TableauBordService;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Anderson Ouattara 2025-10-02
 */
@Service
public class TableauBordServiceImpl implements TableauBordService {

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  private final JpaExtraitsDecesRepository jpaExtraitsDecesRepository;
  private final JpaExtraitsMariagesRepository jpaExtraitsMariagesRepository;
  private final JpaExtraitsNaissancesRepository jpaExtraitsNaissancesRepository;

  public TableauBordServiceImpl(
      JpaExtraitsDecesRepository jpaExtraitsDecesRepository,
      JpaExtraitsMariagesRepository jpaExtraitsMariagesRepository,
      JpaExtraitsNaissancesRepository jpaExtraitsNaissancesRepository
  ) {
    this.jpaExtraitsDecesRepository = jpaExtraitsDecesRepository;
    this.jpaExtraitsMariagesRepository = jpaExtraitsMariagesRepository;
    this.jpaExtraitsNaissancesRepository = jpaExtraitsNaissancesRepository;
  }

  @Override
  public TableauBordExtraitsVM tableauBordExtraits(Long centreId) {
    logger.info("Génération du tableau de bord extrait");
    int nombreExtraitsNaissance = 0;
    if (Objects.nonNull(centreId) && centreId != 0) {
      List<ExtraitNaissancesTable> extraitNaissances = this.jpaExtraitsNaissancesRepository
          .findByCentreId(centreId);
      nombreExtraitsNaissance = extraitNaissances.size();
    }

    List<ExtraitMariagesTable> extraitMariages = this.jpaExtraitsMariagesRepository.findAll();
    int nombreExtraitsMariages = extraitMariages.size();

    List<ExtraitDecesTable> extraitDeces = this.jpaExtraitsDecesRepository.findAll();
    int nombreExtraitsDeces = extraitDeces.size();

    logger.info("Tableau de bord extrait généré");
    return new TableauBordExtraitsVM(
        nombreExtraitsDeces,
        nombreExtraitsMariages,
        nombreExtraitsNaissance
    );
  }
}
