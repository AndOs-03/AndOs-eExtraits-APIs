package com.andos.eextraits.repository;

import com.andos.eextraits.entity.PersonneExtraitNaissanceTable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Anderson Ouattara 2025-08-29
 */
public interface JpaPersonneExtraitNaissanceRepository extends
    JpaRepository<PersonneExtraitNaissanceTable, Long> {

}
