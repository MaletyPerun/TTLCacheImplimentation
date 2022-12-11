package com.example.ttlcacheimplimentation.util;

import com.example.ttlcacheimplimentation.error.BlankException;
import com.example.ttlcacheimplimentation.error.NotFoundException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {

    public static <T> T checkNotNull(T object, String key) {
        if (object == null)
            throw new NotFoundException("Не найдено совпадение ключей/объекта по ключу: " + key);
        return object;
    }

    public static void checkNotBlank(String object) {
        if (object.isBlank())
            throw new BlankException("Ключ не должен быть пустым");
    }
}
