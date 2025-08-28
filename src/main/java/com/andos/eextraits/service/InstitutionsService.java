package com.andos.eextraits.service;

import com.andos.eextraits.dto.commande.CreerInstitutionCommande;
import com.andos.eextraits.dto.vm.InstitutionVM;
import com.andos.eextraits.entity.InstitutionsTable;
import java.util.List;

/**
 * <p>Interface utilisée comme façade pour la gestion des {@link InstitutionsTable}.</p>
 *
 * @author Anderson Ouattara 2025-08-28
 */
public interface InstitutionsService {

  void editer(CreerInstitutionCommande commande);

  void supprimer(Long id);

  InstitutionVM recupererParId(Long id);

  List<InstitutionVM> lister();
}
