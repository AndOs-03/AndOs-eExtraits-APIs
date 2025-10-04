package com.andos.eextraits.config;

import com.andos.eextraits.exception.ExceptionReponseApi;
import com.andos.eextraits.utils.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Anderson Ouattara 2025-09-24
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
    this.jwtService = jwtService;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    var exceptionApi = new ExceptionReponseApi();

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return; // Pas de token → on laisse passer (ex: /login)
    }

    try {
      String jwt = authHeader.substring(7);
      String username = jwtService.extractUsername(jwt);

      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (jwtService.isTokenValid(jwt, userDetails)) {
          Authentication authentication = jwtService.getAuthentification(username, jwt);
          SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
          this.sendErrorResponse(
              exceptionApi,
              HttpStatus.UNAUTHORIZED,
              "Token invalide ou session expirée !"
          );
          return;
        }
      }
    } catch (ExpiredJwtException ex) {
      this.sendErrorResponse(exceptionApi, HttpStatus.UNAUTHORIZED, "Le token a expiré !");
      return;
    } catch (SignatureException ex) {
      this.sendErrorResponse(exceptionApi, HttpStatus.UNAUTHORIZED,
          "Signature du token invalide !");
      return;
    } catch (Exception ex) {
      this.sendErrorResponse(
          exceptionApi,
          HttpStatus.INTERNAL_SERVER_ERROR,
          "Erreur interne d’authentification !"
      );
      return;
    }

    filterChain.doFilter(request, response);
  }

  private void sendErrorResponse(
      ExceptionReponseApi response,
      HttpStatus status,
      String message
  ) {
    response.setStatut(status.value());
    response.setMessage(message);
  }
}
