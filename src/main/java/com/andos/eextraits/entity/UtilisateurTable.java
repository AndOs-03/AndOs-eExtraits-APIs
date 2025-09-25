package com.andos.eextraits.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Anderson Ouattara 2025-09-24
 */
@Getter
@Setter
@Entity
@Table
public class UtilisateurTable implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, unique = true)
  private String nomUtilisateur;
  @Column(nullable = false)
  private String motPasse;
  @Column(nullable = false)
  private String nom;
  @Column(nullable = false)
  private String prenom;
  private String email;
  private boolean verrouille;

  public String nomComplet() {
    return this.nom + " " + this.prenom;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return this.motPasse;
  }

  @Override
  public String getUsername() {
    return this.nomUtilisateur;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
