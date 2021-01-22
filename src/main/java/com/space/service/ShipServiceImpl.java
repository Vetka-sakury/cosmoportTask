package com.space.service;

import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ShipServiceImpl implements ShipService{

    @Autowired
    ShipRepository shipRepository;

    @Override
    public List<Ship> getByFilter() {
        return null;
    }

    @Override
    public List<Ship> getAll() {
        return shipRepository.findAll();
    }

    @Override
    public Integer getShipsCount() {
        return null;
    }

    @Override
    public Ship createShip() {
        return null;
    }

    @Override
    public Ship getById(Long id) {
        return shipRepository.getOne(id);
    }

    @Override
    public void updateShip(Long id) {

    }

    @Override
    public void delete(Long id) {
        shipRepository.deleteById(id);
    }
}
