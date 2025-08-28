package com.andos.eextraits.repository;

import com.andos.eextraits.entity.ExtraitDecesTable;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Anderson Ouattara 2025-08-28
 */
public interface JpaExtraitsDecesRepository extends JpaRepository<ExtraitDecesTable, Long> {

  boolean existsByRegistre(String registre);

  Optional<ExtraitDecesTable> findByRegistre(String registre);
}
