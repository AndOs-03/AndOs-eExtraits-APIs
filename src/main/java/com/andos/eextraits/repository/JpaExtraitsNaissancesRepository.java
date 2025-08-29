package com.andos.eextraits.repository;

import com.andos.eextraits.entity.ExtraitNaissancesTable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Anderson Ouattara 2025-08-29
 */
public interface JpaExtraitsNaissancesRepository extends
    JpaRepository<ExtraitNaissancesTable, Long> {

  boolean existsByRegistreAndCentreId(String registre, Long centreId);

  Optional<ExtraitNaissancesTable> findByRegistreAndCentreId(String registre, Long centreId);

  List<ExtraitNaissancesTable> findByCentreId(Long centreId);
}
