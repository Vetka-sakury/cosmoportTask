package com.space.service;

import com.space.model.Ship;

import java.util.List;

public interface ShipService {
    List <Ship> getByFilter();
    List<Ship> getAll();
    Integer getShipsCount();
    Ship createShip();
    Ship getById(Long id);
    void updateShip(Long id);
    void delete (Long id);
}
