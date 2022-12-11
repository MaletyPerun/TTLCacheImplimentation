package com.example.ttlcacheimplimentation.service;

import com.example.ttlcacheimplimentation.dto.TTLObjectDto;
import com.example.ttlcacheimplimentation.model.TTLObject;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.ttlcacheimplimentation.util.TimeUtil.getTimeStamp;

public class CacheServiceTestData {

    public static final String KEY = "est";
    public static final String KEY_1 = "test-1";
    public static final String VALUE_1 = "test-message-value-1";
    public static final String KEY_2 = "test-2";
    public static final String VALUE_2 = "test-message-value-2";

    public static final String KEY_ADD = "test";
    public static final String VALUE_ADD = "test-add message-value";

    public static final TTLObject TTL_OBJECT = new TTLObject(VALUE_ADD, getTimeStamp());
    public static final TTLObjectDto TTL_OBJECT_DTO = new TTLObjectDto();

    public static TTLObjectDto getTtlObjectDtoData() {
        TTL_OBJECT_DTO.setValue(VALUE_1);
        return TTL_OBJECT_DTO;
    }

    public static final Set<String> SET_OF_KEYS_DATA = Set.of("test-1", "test-2");
}
