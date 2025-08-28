package com.andos.eextraits.service.impl;

import com.andos.eextraits.dto.commande.CreerInstitutionCommande;
import com.andos.eextraits.dto.vm.InstitutionVM;
import com.andos.eextraits.entity.InstitutionsTable;
import com.andos.eextraits.exception.AndOsEExtraitFunctionnalException;
import com.andos.eextraits.exception.ObjetNonTrouveException;
import com.andos.eextraits.repository.JpaInstitutionsRepository;
import com.andos.eextraits.service.InstitutionsService;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Anderson Ouattara 2025-08-28
 */
@Service
public class InstitutionsServiceImpl implements InstitutionsService {

  private final Logger logger = Logger.getLogger(InstitutionsServiceImpl.class.getName());

  private final JpaInstitutionsRepository jpaInstitutionsRepository;

  public InstitutionsServiceImpl(JpaInstitutionsRepository jpaInstitutionsRepository) {
    this.jpaInstitutionsRepository = jpaInstitutionsRepository;
  }

  @Override
  public void editer(CreerInstitutionCommande commande) {
    InstitutionsTable institutionsTable;
    if (Objects.isNull(commande.id())) {
      institutionsTable = new InstitutionsTable();
    } else {
      institutionsTable = this.jpaInstitutionsRepository.findById(commande.id())
          .orElseThrow(() -> new ObjetNonTrouveException("L'institution n'existe pas !"));
    }

    institutionsTable.setDepartement(commande.departement());
    institutionsTable.setCentreEtatCivil(commande.centreEtatCivil());
    institutionsTable.setEtatCivil(commande.etatCivil());
    institutionsTable.setTribunal(commande.tribunal());
    institutionsTable.setVille(commande.ville());
    institutionsTable.setOfficier(commande.officier());
    institutionsTable.setTitreOfficier(commande.titreOfficier());
    this.jpaInstitutionsRepository.save(institutionsTable);
  }

  @Override
  public void supprimer(Long id) {
    try {
      this.jpaInstitutionsRepository.deleteById(id);
    } catch (Exception e) {
      logger.info("###    ERREUR SUPPRESSION INSTITUTION   \n" + e.getMessage());
      throw new AndOsEExtraitFunctionnalException("Impossible de supprimer cette institution !");
    }
  }

  @Override
  public InstitutionVM recupererParId(Long id) {
    InstitutionsTable institutionsTable = this.jpaInstitutionsRepository.findById(id)
        .orElseThrow(() -> new ObjetNonTrouveException("L'institution n'existe pas !"));
    return this.genererInstitutionVm(institutionsTable);
  }

  @Override
  public List<InstitutionVM> lister() {
    List<InstitutionsTable> institutionsTables = this.jpaInstitutionsRepository.findAll();
    return institutionsTables.stream().map(this::genererInstitutionVm).toList();
  }

  private InstitutionVM genererInstitutionVm(InstitutionsTable institutionsTable) {
    return new InstitutionVM(
        institutionsTable.getId(),
        institutionsTable.getDepartement(),
        institutionsTable.getCentreEtatCivil(),
        institutionsTable.getEtatCivil(),
        institutionsTable.getTribunal(),
        institutionsTable.getVille(),
        institutionsTable.getOfficier(),
        institutionsTable.getTitreOfficier()
    );
  }
}
