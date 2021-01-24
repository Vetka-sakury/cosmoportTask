package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

import static com.space.utils.ShipUtil.checkReturnDefault;

public class ShipSpecification {

    public static Specification<Ship> isSameName (String name){
        Specification sp = (root, query, criteriaBuilder) -> name!=null ? criteriaBuilder.like(root.get("name"), "%" + name + "%") : null;
        return sp;
    }

    public static Specification<Ship> isSamePlanet(String planet) {
        Specification sp = (root, query, criteriaBuilder) -> planet !=null ? criteriaBuilder.like(root.get("planet"), "%"+planet+"%") : null;
        return sp;
    }

    public static Specification<Ship> isShipType(ShipType shipType) {
        Specification sp = (root, query, criteriaBuilder) -> shipType!=null ? criteriaBuilder.equal(root.get("shipType"), shipType) : null;
        return sp;
    }

    public static Specification<Ship> isBetweenDate(Long after, Long before) {
        Date afterDate = after == null ? null : new Date(after);
        Date beforeDate = before == null ? null : new Date (before);
        Specification sp = (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("prodDate"),
                checkReturnDefault(afterDate, new Date(0, 1, 1)),
                checkReturnDefault(beforeDate, new Date(1300, 1, 1)));
        return sp;
    }

    public static Specification<Ship> isUsed(Boolean isUsed) {
        Specification sp = (root, query, criteriaBuilder) -> isUsed!=null ? criteriaBuilder.equal(root.get("isUsed"), isUsed) : null;
        return sp;
    }

    public static Specification<Ship> isBetweenMinAndMaxSpeed(Double minSpeed, Double maxSpeed) {
        return (root, query, cb) -> cb.between(root.get("speed"),
                checkReturnDefault(minSpeed, 0.01),
                checkReturnDefault(maxSpeed, 0.99));
    }

    public static Specification<Ship> isBetweenMinAndMaxCrewSize(Integer minCrewSize, Integer maxCrewSize) {
        return (root, query, cb) -> cb.between(root.get("crewSize"),
                checkReturnDefault(minCrewSize, 1),
                checkReturnDefault(maxCrewSize, 9999));
    }

    public static Specification<Ship> isBetweenMinAndMaxRating(Double minRating, Double maxRating) {
        return (root, query, cb) -> cb.between(root.get("rating"),
                checkReturnDefault(minRating, 0.00),
                checkReturnDefault(maxRating, 100.00));
    }

    public static Specification<Ship> allSpecification(String name, String planet,
                                                           ShipType shipType, Boolean isUsed,
                                                           Long after, Long before,
                                                           Double minSpeed, Double maxSpeed,
                                                           Integer minCrewSize, Integer maxCrewSize,
                                                           Double minRating, Double maxRating) {
        return Specification.where(isSameName(name)
                .and(isSamePlanet(planet))
                .and(isShipType(shipType))
                .and(isUsed(isUsed))
                .and(isBetweenDate(after, before))
                .and(isBetweenMinAndMaxSpeed(minSpeed, maxSpeed))
                .and(isBetweenMinAndMaxCrewSize(minCrewSize, maxCrewSize))
                .and(isBetweenMinAndMaxRating(minRating, maxRating)));
    }
}
