package com.andos.eextraits.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Représente le centre qui enregistre les extraits.</p>
 *
 * @author Anderson Ouattara 2025-08-27
 */
@Entity
@Table(name = "centres")
@Getter
@Setter
public class CentresTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String nom;
}
