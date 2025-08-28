package com.andos.eextraits.service;

import com.andos.eextraits.dto.commande.CreerExtraitMariageCommande;
import com.andos.eextraits.dto.commande.ModifierExtraitMariageCommande;
import com.andos.eextraits.dto.vm.ExtraitMariageDetailsVM;
import com.andos.eextraits.dto.vm.ExtraitMariageEssentielVM;
import com.andos.eextraits.entity.ExtraitMariagesTable;
import java.util.List;

/**
 * <p>Interface utilisée comme façade pour la gestion des {@link ExtraitMariagesTable}.</p>
 *
 * @author Anderson Ouattara 2025-08-28
 */
public interface ExtraitsMariagesService {

  void creer(CreerExtraitMariageCommande commande);

  void modifier(ModifierExtraitMariageCommande commande);

  void supprimer(Long id);

  ExtraitMariageDetailsVM recupererParId(Long id);

  ExtraitMariageDetailsVM recupererParRegistre(String registre);

  List<ExtraitMariageEssentielVM> lister();
}
