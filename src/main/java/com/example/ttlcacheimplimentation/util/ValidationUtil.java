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

    public static String[] checkContent(String[] stringArrayOfCommandLine) {
        if (stringArrayOfCommandLine.length < 2)
            throw new NoContentException("Ключ без объекта");
        return stringArrayOfCommandLine;
    }
}
