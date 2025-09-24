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
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
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

  public String genererToken(UserDetails userDetails) {
    Date dateActuelle = new Date(System.currentTimeMillis());
    Date expiration = this.dateExpiration(dateActuelle);
    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .setIssuedAt(dateActuelle)
        .setExpiration(expiration)
        .signWith(getSigingKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  private Date dateExpiration(Date dateActuelle) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(dateActuelle);
    calendar.add(Calendar.DAY_OF_MONTH, 1);
    return calendar.getTime();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractTokenExpiration(token).before(new Date());
  }

  private Date extractTokenExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
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
    User principal = new User(nomUtilisateur, "", new HashSet<>());
    return new UsernamePasswordAuthenticationToken(principal, token, new HashSet<>());
  }

  public UtilisateurTable recupererUtilisateur(String nomUtilisateur) {
    return this.jpaUtilisateurRepository.findByNomUtilisateur(nomUtilisateur)
        .orElseThrow(() -> new ObjetNonTrouveException("L'utilisateur est introuvable !"));
  }
}
