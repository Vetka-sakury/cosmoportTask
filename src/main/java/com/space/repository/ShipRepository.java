package com.space.repository;

import com.space.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
/**
 * Repository for {@link Ship} class.
 */
@Repository

public interface ShipRepository extends JpaRepository <Ship, Long>, JpaSpecificationExecutor<Ship> {

}
