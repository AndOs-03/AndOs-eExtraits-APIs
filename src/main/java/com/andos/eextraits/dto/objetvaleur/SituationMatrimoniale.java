package com.andos.eextraits.dto.objetvaleur;

/**
 * @author Anderson Ouattara 2025-08-27
 */
public enum SituationMatrimoniale {

  MARIEE {
    @Override
    public String toString() {
      return "Mariée";
    }
  },

  CELIBATAIRE {
    @Override
    public String toString() {
      return "Célibataire";
    }
  },

  VEUVE {
    @Override
    public String toString() {
      return "Veuve";
    }
  },

  DIVORCEE {
    @Override
    public String toString() {
      return "Divorcée";
    }
  }
}
