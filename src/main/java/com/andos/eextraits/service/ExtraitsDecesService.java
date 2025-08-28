package com.andos.eextraits.service;

import com.andos.eextraits.dto.commande.CreerExtraitDecesCommande;
import com.andos.eextraits.dto.commande.ModifierExtraitDecesCommande;
import com.andos.eextraits.dto.vm.ExtraitDecesDetailsVM;
import com.andos.eextraits.dto.vm.ExtraitDecesEssentielVM;
import com.andos.eextraits.entity.CentresTable;
import java.util.List;

/**
 * <p>Interface utilisée comme façade pour la gestion des {@link CentresTable}.</p>
 *
 * @author Anderson Ouattara 2025-08-27
 */
public interface ExtraitsDecesService {

  void creer(CreerExtraitDecesCommande commande);

  void modifier(ModifierExtraitDecesCommande commande);

  void supprimer(Long id);

  ExtraitDecesDetailsVM recupererParId(Long id);

  ExtraitDecesDetailsVM recupererParRegistre(String registre);

  List<ExtraitDecesEssentielVM> lister();
}
