package com.andos.eextraits.repository;

import com.andos.eextraits.entity.CentresTable;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Anderson Ouattara 2025-08-27
 */
public interface JpaCentresRepository extends JpaRepository<CentresTable, Long> {

  boolean existsByNom(String nom);

  Optional<CentresTable> findByNom(String nom);
}
