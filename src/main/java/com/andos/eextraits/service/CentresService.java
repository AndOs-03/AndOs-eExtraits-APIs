package com.andos.eextraits.service;

import com.andos.eextraits.dto.commande.CreerCentreCommande;
import com.andos.eextraits.dto.commande.ModifierCentreCommande;
import com.andos.eextraits.dto.vm.CentreVM;
import com.andos.eextraits.entity.CentresTable;
import java.util.List;

/**
 * <p>Interface utilisée comme façade pour la gestion des {@link CentresTable}.</p>
 *
 * @author Anderson Ouattara 2025-08-27
 */
public interface CentresService {

  void creer(CreerCentreCommande commande);

  void modifier(ModifierCentreCommande commande);

  void supprimer(Long id);

  CentreVM recupererParId(Long id);

  CentreVM recupererParNom(String nom);

  List<CentreVM> lister();
}
