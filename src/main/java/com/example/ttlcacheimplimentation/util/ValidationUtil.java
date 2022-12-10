package com.example.ttlcacheimplimentation.util;

import com.example.ttlcacheimplimentation.error.NotFoundException;
import com.example.ttlcacheimplimentation.error.NoContentException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {

    public static <T> T checkNotNull(T object, String key) {
        if (object == null)
            throw new NotFoundException("Не найдено совпадение ключей/объекта по ключу: " + key);
        return object;
    }

    public static <T> T checkNotNull(T object) {
        if (object == null)
            throw new NoContentException("Пустое значение объекта");
        return object;
    }

    public static void checkNotBlank(String str) {
        if (str.isBlank())
            throw new NotFoundException("Ключ не должен быть пустым");
    }
}
