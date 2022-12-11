package com.example.ttlcacheimplimentation.util;

import com.example.ttlcacheimplimentation.dto.TTLObjectDto;
import com.example.ttlcacheimplimentation.model.TTLObject;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TTLObjectUtil {

    public static TTLObjectDto createNewObjectDTO(TTLObject ttlObject) {
        TTLObjectDto ttlObjectDto = new TTLObjectDto();
        new TTLObjectDto().setObject(ttlObject.getObject());
        return ttlObjectDto;
    }
}
