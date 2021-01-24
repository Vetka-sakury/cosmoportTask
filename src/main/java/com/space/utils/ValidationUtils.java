package com.space.utils;

import org.springframework.util.StringUtils;
import com.space.model.Ship;

public class ValidationUtils {

    public static boolean validateId (Long id){
        return id != null && id != 0;
    }

    public static boolean checkEmptyBody (Ship ship){
        return StringUtils.isEmpty(ship.getName())&&
                StringUtils.isEmpty(ship.getPlanet()) &&
                ship.getShipType() == null &&
                ship.getProdDate() == null &&
                ship.getSpeed() == null &&
                ship.getCrewSize() == null;
    }

    public static boolean validateShipDataForUpdate (Ship ship){
        return (ship.getName()==null || (ship.getName().length() <=50 && ship.getName().length()>0)
        && (ship.getPlanet() == null || ship.getPlanet().length() <=50)
                && (ship.getProdDate() == null || ship.getProdDate().getTime() >=0)
                && (ship.getSpeed() == null || (ship.getSpeed() >=0.01 && ship.getSpeed() <=0.99))
                && (ship.getCrewSize() == null || (ship.getCrewSize()>=1 && ship.getCrewSize()<=9999)));
    }

    public static boolean validateShipDataForCreate (Ship ship){
        return (ship.getName()!=null && ship.getName().length() <=50 && ship.getName().length()>0
                && ship.getPlanet()!=null && ship.getPlanet().length() <=50 && ship.getPlanet().length()>0
                && ship.getSpeed()!=null && ship.getSpeed() >=0.01 && ship.getSpeed() <=0.99
                && ship.getCrewSize()!=null && ship.getCrewSize()>=1 && ship.getCrewSize()<=9999
                && ship.getProdDate()!=null && ship.getProdDate().getTime() >=0 && ship.getProdDate().getYear()<3700
                && ship.getShipType() != null);
    }


}
