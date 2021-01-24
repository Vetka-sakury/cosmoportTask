package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import com.space.service.ShipSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.space.utils.ShipUtil.transformDataForUpdate;
import static com.space.utils.ValidationUtils.*;

@RestController
@RequestMapping(value = "/rest/ships", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

public class ShipController {

    @Autowired
    ShipService shipService;

    @GetMapping("/count")
    public ResponseEntity<Long> getCountFiltered(@RequestParam(required = false) String name, @RequestParam(required = false) String planet,
                                 @RequestParam(required = false) ShipType shipType, @RequestParam(required = false) Boolean isUsed,
                                 @RequestParam(required = false) Long after, @RequestParam(required = false) Long before,
                                 @RequestParam(required = false) Double minSpeed, @RequestParam(required = false) Double maxSpeed,
                                 @RequestParam(required = false) Integer minCrewSize, @RequestParam(required = false) Integer maxCrewSize,
                                 @RequestParam(required = false) Double minRating, @RequestParam(required = false) Double maxRating) {
        Long count = shipService.getShipsCount(ShipSpecification.allSpecification(name, planet, shipType, isUsed,
                after, before, minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating));
        return new ResponseEntity<>(count, HttpStatus.OK);
    }


    @GetMapping(value = "")
    public ResponseEntity<List<Ship>> getShipsByFilter (@RequestParam(required = false) String name, @RequestParam(required = false) String planet,
                                        @RequestParam(required = false) ShipType shipType, @RequestParam(required = false) Boolean isUsed,
                                        @RequestParam(required = false) Long after, @RequestParam(required = false) Long before,
                                        @RequestParam(required = false) Double minSpeed, @RequestParam(required = false) Double maxSpeed,
                                        @RequestParam(required = false) Integer minCrewSize, @RequestParam(required = false) Integer maxCrewSize,
                                        @RequestParam(required = false) Double minRating, @RequestParam(required = false) Double maxRating,
                                        @RequestParam(defaultValue = "ID") ShipOrder order,
                                        @RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "3") Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, order.getFieldName());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        List<Ship> list = shipService.getByFilter(ShipSpecification.allSpecification(name, planet, shipType, isUsed, after, before, minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating), pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ship> createShip (@RequestBody Ship ship){
        if (checkEmptyBody(ship)
                || !validateShipDataForCreate(ship))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(shipService.createShip(ship), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity <Ship> updateShip (@RequestBody Ship ship, @PathVariable Long id){
        if (!validateId(id) || ship == null){
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
        }
        if (!validateShipDataForUpdate(ship)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Ship shipFound = shipService.getById(id);
        if (checkEmptyBody(ship)){
            return new ResponseEntity<>(shipFound, HttpStatus.OK);
        }
        if (shipFound == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Ship updated = shipService.update(transformDataForUpdate(shipFound, ship), id);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity <Ship> getShipById (@PathVariable Long id){
        if (!validateId(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Ship ship = shipService.getById(id);
        if (ship == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity <Ship> deleteShip (@PathVariable Long id){

        if (!validateId(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Ship ship = shipService.getById(id);
        if (ship == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        shipService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
