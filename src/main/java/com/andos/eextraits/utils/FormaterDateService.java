package com.andos.eextraits.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Anderson Ouattara 2025-08-28
 */
@Getter
@Setter
public class FormaterDateService {

  private SimpleDateFormat formatter;
  private Date date;

  public String formater() {
    return this.formatter.format(this.date);
  }

  /**
   * <p>Formate la date au format 13 April 2015.</p>
   *
   * @param date la date à formater
   * @return la date au format 13 April 2015
   */
  public String formater(Date date) {
    var formatter = new SimpleDateFormat("dd MMMM yyyy");
    return formatter.format(date);
  }

  /**
   * <p>Formate la date au format 13 April 2015 à 00:00:00.</p>
   *
   * @param date la date à formater
   * @return la date au format 13 April 2015
   */
  public String formatJourMoisAnneeHeureLong(Date date) {
    var formatter = new SimpleDateFormat("dd MMMM yyyy à HH:mm:ss");
    return formatter.format(date);
  }

  /**
   * <p>Formate la date au format 01/01/2000 10:00:00.</p>
   *
   * @param date la date à formater
   * @return la date au format 01/01/2000 10:00:00
   */
  public String formatJourMoisAnneeHeure(Date date) {
    var formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    return formatter.format(date);
  }

  /**
   * <p>Formate la date au format 01/01/2000.</p>
   *
   * @param date la date à formater
   * @return la date au format 01/01/2000
   */
  public String formatJourMoisAnnee(Date date) {
    var formatter = new SimpleDateFormat("dd/MM/yyyy");
    return formatter.format(date);
  }

  /**
   * <p>Formate la date au format 01/01/2000.</p>
   *
   * @param localDate la date à formater
   * @return la date au format 01/01/2000
   */
  public String formatJourMoisAnnee(LocalDate localDate) {
    var formatter = new SimpleDateFormat("dd/MM/yyyy");
    ZoneId defaultZoneId = ZoneId.systemDefault();
    Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
    return formatter.format(date);
  }

  /**
   * <p>Formate la date au format Janvier 2000.</p>
   *
   * @param anneeMois l'année et le mois à formater
   * @return la date au format janvier 2000
   */
  public static String formatMoisAnnee(YearMonth anneeMois) {
    var formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
    return anneeMois.format(formatter);
  }

  /**
   * <p>Formate la date au format 2000-12.</p>
   *
   * @param mois  le mois à formater
   * @param annee l'année à formater
   * @return la date au format 2000-12
   */
  public static YearMonth convertirVersYearMonth(int mois, int annee) {
    return YearMonth.of(annee, mois);
  }
}
