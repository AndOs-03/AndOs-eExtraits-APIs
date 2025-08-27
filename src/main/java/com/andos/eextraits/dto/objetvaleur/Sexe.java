package com.andos.eextraits.dto.objetvaleur;

/**
 * @author Anderson Ouattara 2025-08-27
 */
public enum Sexe {

  MASCULIN {
    @Override
    public String toString() {
      return "Masculin";
    }
  },

  FEMININ {
    @Override
    public String toString() {
      return "Féminin";
    }
  },
}
