package com.andos.eextraits.service.impl;

import com.andos.eextraits.dto.commande.ConnexionCommande;
import com.andos.eextraits.dto.commande.CreerUtilisateurCommande;
import com.andos.eextraits.entity.UtilisateurTable;
import com.andos.eextraits.exception.AndOsEExtraitFunctionnalException;
import com.andos.eextraits.repository.JpaUtilisateurRepository;
import com.andos.eextraits.service.UtilisateurService;
import com.andos.eextraits.utils.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Anderson Ouattara 2025-09-24
 */
@Service
public class UtilisateurServiceImpl implements UtilisateurService {

  private final JpaUtilisateurRepository jpaUtilisateurRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  public UtilisateurServiceImpl(
      JpaUtilisateurRepository jpaUtilisateurRepository,
      PasswordEncoder passwordEncoder,
      AuthenticationManager authenticationManager,
      JwtService jwtService
  ) {
    this.jpaUtilisateurRepository = jpaUtilisateurRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
  }

  @Override
  public void creer(CreerUtilisateurCommande commande) {
    String userName = commande.nomUtilisateur();
    boolean exists = this.jpaUtilisateurRepository.existsByNomUtilisateur(userName);
    if (exists) {
      throw new AndOsEExtraitFunctionnalException("Ce nom d'utilisateur existe déjà !");
    }

    var utilisateurTable = new UtilisateurTable();
    utilisateurTable.setNomUtilisateur(userName);
    utilisateurTable.setMotPasse(passwordEncoder.encode(commande.motPasse()));
    this.jpaUtilisateurRepository.save(utilisateurTable);
  }

  @Override
  public String connexion(ConnexionCommande commande) {
    String userName = commande.nomUtilisateur();
    Authentication auth = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(userName, commande.motPasse())
    );
    UserDetails userDetails = (UserDetails) auth.getPrincipal();
    return jwtService.genererToken(userDetails);
  }
}
