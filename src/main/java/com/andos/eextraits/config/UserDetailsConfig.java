package com.andos.eextraits.config;

import com.andos.eextraits.exception.ObjetNonTrouveException;
import com.andos.eextraits.repository.JpaUtilisateurRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Anderson Ouattara 2025-09-24
 */
@Configuration
public class UserDetailsConfig implements UserDetailsService {

  private final JpaUtilisateurRepository jpaUtilisateurRepository;

  public UserDetailsConfig(JpaUtilisateurRepository jpaUtilisateurRepository) {
    this.jpaUtilisateurRepository = jpaUtilisateurRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.jpaUtilisateurRepository.findByNomUtilisateur(username)
        .map(user -> User.builder()
            .username(user.getNomUtilisateur())
            .password(user.getMotPasse())
            .build())
        .orElseThrow(() -> new ObjetNonTrouveException("Utilisateur non trouvé: " + username));
  }
}
