package com.example.demo.utils;

import com.example.demo.exceptions.RecordNotFoundException;
import com.example.demo.models.IdEntity;

public class ValidatorUtil {
    public static void validateDbRecord(IdEntity entity, String exceptionMessage) {
        if(entity == null) throw new RecordNotFoundException(exceptionMessage);
    }
}
