package com.example.ttlcacheimplimentation.util;

import com.example.ttlcacheimplimentation.dto.TTLObjectDto;
import com.example.ttlcacheimplimentation.model.TTLObject;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TTLObjectUtil {

    public static TTLObjectDto createNewObjectDTO(TTLObject object) {
        return new TTLObjectDto(object.getObject());
    }
}
