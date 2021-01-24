package com.space.service;

import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.space.utils.ShipUtil.calculateRating;
import static com.space.utils.ShipUtil.roundDouble;

@Service

public class ShipServiceImpl implements ShipService{

    @Autowired
    ShipRepository shipRepository;

    @Override
    public Long getShipsCount (Specification<Ship> specification){
        return shipRepository.count(specification);
    }

    @Override
    public List<Ship> getByFilter(Specification<Ship> specification, Pageable page){
        return shipRepository.findAll(specification, page).getContent();
    }

    @Override
    public Ship createShip(Ship ship) {
        ship.setUsed(ship.getUsed()!=null && ship.getUsed());
        ship.setSpeed(roundDouble(ship.getSpeed()));
        ship.setRating(calculateRating(ship));
        return shipRepository.save(ship);
    }

    @Override
    public Ship getById(Long id) {
        return shipRepository.findById(id).orElse(null);
    }

    @Override
    public Ship update(Ship ship, Long id) {
        ship.setSpeed(roundDouble(ship.getSpeed()));
        ship.setRating(calculateRating(ship));
        return shipRepository.save(ship);
    }

    @Override
    public void delete(Long id) {
        shipRepository.deleteById(id);
    }
}
