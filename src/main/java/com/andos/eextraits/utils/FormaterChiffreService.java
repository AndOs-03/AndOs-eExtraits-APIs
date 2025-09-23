package com.andos.eextraits.utils;

import com.ibm.icu.text.RuleBasedNumberFormat;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import net.sf.jasperreports.engine.JRDefaultScriptlet;

/**
 * <p>Classe utilitaire pour le formatage des chiffres.</p>
 *
 * @author Anderson Ouattara 2023-01-24
 */
public class FormaterChiffreService extends JRDefaultScriptlet {

  public String ajouterSeparateurDeMillier(Number chaine) {
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    symbols.setGroupingSeparator(' ');
    DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
    return formatter.format(chaine);
  }

  public static String ajouterSeparateurMillier(Number chaine) {
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    symbols.setGroupingSeparator(' ');
    DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
    return formatter.format(chaine);
  }

  public static String convertirValeurEnLettre(BigDecimal montant) {
    var formatter = new RuleBasedNumberFormat(Locale.FRENCH, RuleBasedNumberFormat.SPELLOUT);
    return formatter.format(montant);
  }
}
