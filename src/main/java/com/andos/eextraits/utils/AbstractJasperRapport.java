package com.andos.eextraits.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.core.io.ClassPathResource;


/**
 * {@inheritDoc}
 *
 * @author Anderson Ouattara 2025-09-22
 */

public abstract class AbstractJasperRapport {

  private final Map<String, Object> parametres;
  private JasperReport rapport;
  private JRBeanCollectionDataSource dataSource;

  protected AbstractJasperRapport() {
    this.parametres = new HashMap<>();
  }

  /**
   * Initialiser un rapport.
   *
   * @param path chemin du rapport
   */
  public void initialisation(String path) {
    try {
      this.rapport = this.obtenirRapport(path);
    } catch (JRException | IOException e) {
      throw new RuntimeException("Fichier de rapport introuvable");
    }
  }

  private JasperReport obtenirRapport(String path) throws JRException, IOException {
    InputStream inputStream = this.obtenirRapportInputStream(path);
    return JasperCompileManager.compileReport(inputStream);
  }

  private InputStream obtenirRapportInputStream(String path) throws IOException {
    var classPathResource = new ClassPathResource(path);
    return classPathResource.getInputStream();
  }

  public ByteArrayOutputStream exporter() {
    return this.renderPDF();
  }

  public ByteArrayOutputStream renderPDF() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      JasperPrint jasperPrint = this.obtenirJasperPrint();
      JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    } catch (JRException e) {
      throw new RuntimeException(e.getMessage());
    }
    return outputStream;
  }

  public ByteArrayOutputStream renderExcel() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      JasperPrint jasperPrint = this.obtenirJasperPrint();
      JRXlsxExporter xlsxExporter = new JRXlsxExporter();
      xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
      SimpleXlsxReportConfiguration xlsxReportConfiguration = new SimpleXlsxReportConfiguration();
      xlsxReportConfiguration.setOnePagePerSheet(false);
      xlsxReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
      xlsxReportConfiguration.setDetectCellType(false);
      xlsxReportConfiguration.setWhitePageBackground(false);
      xlsxExporter.setConfiguration(xlsxReportConfiguration);
      xlsxExporter.exportReport();
    } catch (JRException e) {
      throw new RuntimeException(e.getMessage());
    }
    return outputStream;
  }

  public ByteArrayOutputStream renderHTML() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      JasperPrint jasperPrint = this.obtenirJasperPrint();
      HtmlExporter exporter = new HtmlExporter();
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      exporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
      SimpleHtmlExporterConfiguration configuration = new SimpleHtmlExporterConfiguration();
      exporter.setConfiguration(configuration);
      exporter.exportReport();
    } catch (JRException e) {
      throw new RuntimeException(e.getMessage());
    }
    return outputStream;
  }

  private JasperPrint obtenirJasperPrint() throws JRException {
    return JasperFillManager.fillReport(this.rapport, this.parametres, this.dataSource);
  }

  public void ajouterSousRapport(String key, String path)
      throws JRException, IOException {
    this.ajouterParametres(key, this.obtenirRapport(path));
  }

  public void ajouterParametres(String key, Object valeur) {
    this.parametres.put(key, valeur);
  }

  public void ajouterParametres(Map<String, Object> parametres) {
    this.parametres.putAll(parametres);
  }

  public Map<String, Object> getParametres() {
    return parametres;
  }

  public JasperReport getFichierDeBase() {
    return rapport;
  }

  public JRBeanCollectionDataSource getDataSource() {
    return dataSource;
  }

  public void setDataSource(JRBeanCollectionDataSource dataSource) {
    this.dataSource = dataSource;
  }
}
