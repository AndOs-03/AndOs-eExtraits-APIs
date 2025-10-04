package com.andos.eextraits.utils;

import com.andos.eextraits.entity.UtilisateurTable;
import com.andos.eextraits.exception.ObjetNonTrouveException;
import com.andos.eextraits.repository.JpaUtilisateurRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author Anderson Ouattara 2025-09-24
 */
@Component
public class JwtService {

  private final JpaUtilisateurRepository jpaUtilisateurRepository;
  @Value("${application.security.jwt.secret-key}")
  private String secretKey;

  public JwtService(JpaUtilisateurRepository jpaUtilisateurRepository) {
    this.jpaUtilisateurRepository = jpaUtilisateurRepository;
  }

  public String genererToken(UtilisateurTable userDetails) {
    Date dateActuelle = Date.from(LocalDateTime.now()
        .atZone(ZoneId.systemDefault())
        .toInstant());
    Date expiration = this.dateExpiration();
    return Jwts.builder()
        .setSubject(userDetails.getId().toString())
        .claim("username", userDetails.getUsername())
        .setIssuedAt(dateActuelle)
        .setExpiration(expiration)
        .signWith(getSigingKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  private Date dateExpiration() {
    return Date.from(LocalDateTime.now().plusDays(1)
        .atZone(ZoneId.systemDefault())
        .toInstant());
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    String tokenUsername = extractAllClaims(token).get("username", String.class);
    return tokenUsername.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    Date expiration = extractAllClaims(token).getExpiration();
    return expiration.before(new Date());
  }

  public String extractUsername(String token) {
    return extractAllClaims(token).get("username", String.class);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigingKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getSigingKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public Authentication getAuthentification(String nomUtilisateur, String token) {
    UserDetails userDetails = this.recupererUtilisateur(nomUtilisateur);

    return new UsernamePasswordAuthenticationToken(
        userDetails,
        token,
        userDetails.getAuthorities()
    );
  }

  public UtilisateurTable recupererUtilisateur(String nomUtilisateur) {
    return this.jpaUtilisateurRepository.findByNomUtilisateur(nomUtilisateur)
        .orElseThrow(() -> new ObjetNonTrouveException("L'utilisateur est introuvable !"));
  }
}
