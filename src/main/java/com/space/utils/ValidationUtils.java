package com.space.utils;

public class ValidationUtils {

    public static boolean validateId (Long id){
        return id != null && id != 0;
    }
}
