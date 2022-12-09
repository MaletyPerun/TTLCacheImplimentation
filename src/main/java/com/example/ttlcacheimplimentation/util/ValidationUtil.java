package com.example.ttlcacheimplimentation.util;

import com.example.ttlcacheimplimentation.error.NotFoundException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {

    public static <T> T checkNotNull(T object, String key) {
        if (object == null)
            throw new NotFoundException("Not found keys/object with key: " + key);
        return object;
    }
}
