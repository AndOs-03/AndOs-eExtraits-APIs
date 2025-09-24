package com.andos.eextraits.repository;

import com.andos.eextraits.entity.UtilisateurTable;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Anderson Ouattara 2025-09-24
 */
public interface JpaUtilisateurRepository extends JpaRepository<UtilisateurTable, Long> {

  boolean existsByNomUtilisateur(String nom);

  Optional<UtilisateurTable> findByNomUtilisateur(String nom);
}
