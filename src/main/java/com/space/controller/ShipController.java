package com.space.controller;

import com.space.model.Ship;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.space.utils.ValidationUtils.validateId;

@RestController
@RequestMapping(value = "/rest/ships", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

public class ShipController {

    @Autowired
    ShipService shipService;

    @GetMapping (value = "/{id}")
    public ResponseEntity <Ship> getShipById (@PathVariable Long shipId){
        if (!validateId(shipId)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Ship ship = shipService.getById(shipId);
        if (ship == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity <List <Ship>> getAllShips(){
        List <Ship> ships= shipService.getAll();
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity <Ship> deleteShip (@PathVariable Long shipId){

        if (!validateId(shipId)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Ship ship = shipService.getById(shipId);
        if (ship == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        shipService.delete(shipId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
