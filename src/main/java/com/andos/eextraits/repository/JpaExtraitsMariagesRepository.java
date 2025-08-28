package com.andos.eextraits.repository;

import com.andos.eextraits.entity.ExtraitDecesTable;
import com.andos.eextraits.entity.ExtraitMariagesTable;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Anderson Ouattara 2025-08-28
 */
public interface JpaExtraitsMariagesRepository extends JpaRepository<ExtraitMariagesTable, Long> {

  boolean existsByRegistre(String registre);

  Optional<ExtraitMariagesTable> findByRegistre(String registre);
}
