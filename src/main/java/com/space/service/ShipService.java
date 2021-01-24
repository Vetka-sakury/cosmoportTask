package com.space.service;


import com.space.model.Ship;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ShipService {
    List <Ship> getByFilter(Specification<Ship> specification, Pageable page);
    Long getShipsCount (Specification<Ship> specification);
    Ship createShip(Ship ship);
    Ship getById(Long id);
    Ship update(Ship ship, Long id);
    void delete (Long id);
}
