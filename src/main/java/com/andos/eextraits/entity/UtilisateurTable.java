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
 * @author Anderson Ouattara 2025-09-24
 */
@Getter
@Setter
@Entity
@Table
public class UtilisateurTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String email;
  @Column(nullable = false, unique = true)
  private String nomUtilisateur;
  @Column(nullable = false)
  private String motPasse;
}
