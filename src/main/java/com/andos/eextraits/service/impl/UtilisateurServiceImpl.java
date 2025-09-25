package com.andos.eextraits.service.impl;

import com.andos.eextraits.dto.commande.ConnexionCommande;
import com.andos.eextraits.dto.commande.CreerUtilisateurCommande;
import com.andos.eextraits.dto.vm.JwtToken;
import com.andos.eextraits.entity.UtilisateurTable;
import com.andos.eextraits.exception.AndOsEExtraitFunctionnalException;
import com.andos.eextraits.repository.JpaUtilisateurRepository;
import com.andos.eextraits.service.UtilisateurService;
import com.andos.eextraits.utils.JwtService;
import java.util.logging.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Anderson Ouattara 2025-09-24
 */
@Service
public class UtilisateurServiceImpl implements UtilisateurService {

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  private final JpaUtilisateurRepository jpaUtilisateurRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final JwtService jwtService;

  public UtilisateurServiceImpl(
      JpaUtilisateurRepository jpaUtilisateurRepository,
      PasswordEncoder passwordEncoder,
      AuthenticationManagerBuilder authenticationManagerBuilder,
      JwtService jwtService
  ) {
    this.jpaUtilisateurRepository = jpaUtilisateurRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManagerBuilder = authenticationManagerBuilder;
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
    utilisateurTable.setNom(commande.nom());
    utilisateurTable.setPrenom(commande.prenom());
    utilisateurTable.setEmail(commande.email());
    this.jpaUtilisateurRepository.save(utilisateurTable);
    logger.info("Compte utilisateur créer pour : " + userName);
  }

  @Override
  public JwtToken connexion(ConnexionCommande commande) {
    String userName = commande.nomUtilisateur();
    UtilisateurTable utilisateurTable = this.jpaUtilisateurRepository.findByNomUtilisateur(userName)
        .orElseThrow(() -> new AndOsEExtraitFunctionnalException("Email ou mot de passe incorrect"
            + " !"));

    String motPasse = commande.motPasse();
    var authenticationToken = new UsernamePasswordAuthenticationToken(userName, motPasse);
    AuthenticationManager authenticationManager = authenticationManagerBuilder.getObject();
    Authentication authentication = authenticationManager.authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = jwtService.genererToken(utilisateurTable);
    String nomComplet = utilisateurTable.nomComplet();

    JwtToken jwtToken = new JwtToken(token, userName, nomComplet);
    jwtToken.setUserId(utilisateurTable.getId());
    logger.info("Connexion de l'utilisateur : " + userName);
    return jwtToken;
  }
}
