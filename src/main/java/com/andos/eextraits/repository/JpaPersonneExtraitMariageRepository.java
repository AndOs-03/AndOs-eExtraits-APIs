package com.andos.eextraits.repository;

import com.andos.eextraits.entity.PersonneExtraitMariageTable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Anderson Ouattara 2025-08-28
 */
public interface JpaPersonneExtraitMariageRepository extends
    JpaRepository<PersonneExtraitMariageTable, Long> {

}
