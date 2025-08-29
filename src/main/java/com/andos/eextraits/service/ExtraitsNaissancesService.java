package com.andos.eextraits.service;

import com.andos.eextraits.dto.commande.CreerExtraitNaissanceCommande;
import com.andos.eextraits.dto.commande.ModifierExtraitNaissanceCommande;
import com.andos.eextraits.dto.vm.ExtraitNaissanceDetailsVM;
import com.andos.eextraits.dto.vm.ExtraitNaissanceEssentielVM;
import com.andos.eextraits.entity.ExtraitNaissancesTable;
import java.util.List;

/**
 * <p>Interface utilisée comme façade pour la gestion des {@link ExtraitNaissancesTable}.</p>
 *
 * @author Anderson Ouattara 2025-08-29
 */
public interface ExtraitsNaissancesService {

  void creer(CreerExtraitNaissanceCommande commande);

  void modifier(ModifierExtraitNaissanceCommande commande);

  void supprimer(Long id);

  ExtraitNaissanceDetailsVM recupererParId(Long id);

  ExtraitNaissanceDetailsVM recupererParRegistre(String registre, Long centreId);

  List<ExtraitNaissanceEssentielVM> lister(Long centreId);
}
