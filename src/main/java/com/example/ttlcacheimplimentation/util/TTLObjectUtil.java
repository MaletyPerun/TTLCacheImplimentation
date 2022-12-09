package com.example.ttlcacheimplimentation.util;

import com.example.ttlcacheimplimentation.dto.TTLObjectDTO;
import com.example.ttlcacheimplimentation.model.TTLObject;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TTLObjectUtil {

    public static TTLObjectDTO createNewObjectDTO(TTLObject object) {
        return new TTLObjectDTO(object.getObject());
    }
}
