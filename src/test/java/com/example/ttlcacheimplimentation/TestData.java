package com.example.ttlcacheimplimentation;

import com.example.ttlcacheimplimentation.dto.TTLObjectDto;
import com.example.ttlcacheimplimentation.model.TTLObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static com.example.ttlcacheimplimentation.util.TimeUtil.getTimeStamp;

public class TestData {
    public static final String KEY_1 = "test-1";
    public static final String VALUE_1 = "test-message-value-1";
    public static final String KEY_2 = "test-2";
    public static final String VALUE_2 = "test-message-value-2";
    public static final String KEY_ADD = "test";
    public static final String VALUE_ADD = "test-add message-value";
    public static final String GET_URL = "/GET?key=test-1";
    public static final String DELETE_URL = "/DEL?key=test-1";
    public static final String KEY = "est";
    public static final String KEYS_URL = "/KEYS?key=" + KEY;
    public static final String SET_URL = "/SET?key=test";
    public static final String SET_RESPONSE = "Установлен ключ: " + KEY_ADD + " со значением: " + VALUE_ADD;
    public static final String DELETE_RESPONSE = "Удален ключ: " + KEY_1 + " со значением: " + VALUE_1;
    public static final Set<String> KEYS_RESPONSE = Set.of("test-2", "test-1");
    public static final TTLObject TTL_OBJECT = new TTLObject(VALUE_ADD, getTimeStamp());
    public static final TTLObjectDto TTL_OBJECT_DTO = new TTLObjectDto();
    public static TTLObjectDto getTtlObjectDtoData() {
        TTL_OBJECT_DTO.setValue(VALUE_1);
        return TTL_OBJECT_DTO;
    }
    public static TTLObjectDto setTtlObjectDtoData() {
        TTL_OBJECT_DTO.setValue(VALUE_ADD);
        return TTL_OBJECT_DTO;
    }
    public static ResponseEntity<TTLObjectDto> GET_TTL_OBJECT_DTO = new ResponseEntity<>(getTtlObjectDtoData(), HttpStatus.OK);

}
