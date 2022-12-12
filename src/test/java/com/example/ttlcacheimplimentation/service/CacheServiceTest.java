package com.example.ttlcacheimplimentation.service;

import com.example.ttlcacheimplimentation.dto.TTLObjectDto;
import com.example.ttlcacheimplimentation.error.NotFoundException;
import com.example.ttlcacheimplimentation.model.TTLObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static com.example.ttlcacheimplimentation.TestData.*;
import static com.example.ttlcacheimplimentation.util.TimeUtil.getTimeStamp;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CacheServiceTest {

    @Autowired
    private CacheService cacheService;

    @BeforeEach
    public void setUp() {
        cacheService.add(KEY_1, VALUE_1);
        cacheService.add(KEY_2, VALUE_2);
    }

    @AfterEach
    public void tearDown() {
        cacheService.clearAllCache();
    }

    @Test
    void get() {
        TTLObjectDto ttlObjectDto = cacheService.get(KEY_1);
        TTLObjectDto ttlObjectDtoData = getTtlObjectDtoData();
        assertEquals(ttlObjectDtoData.getValue(), ttlObjectDto.getValue());
    }

    @Test
    void add() {
        TTL_OBJECT.setTimeStamp(getTimeStamp());
        TTLObject ttlObject = cacheService.add(KEY_ADD, VALUE_ADD);
        assertEquals(ttlObject.getValue(), TTL_OBJECT.getValue());
        assertEquals(TTL_OBJECT.getTimeStamp(), ttlObject.getTimeStamp());
    }

    @Test
    void getKeys() {
        assertEquals(KEYS_RESPONSE, cacheService.getKeys(KEY));
    }

    @Test
    void delete() {
        TTLObjectDto ttlObjectDto = cacheService.delete(KEY_1);
        TTLObjectDto ttlObjectDtoData = getTtlObjectDtoData();
        assertEquals(ttlObjectDtoData.getValue(), ttlObjectDto.getValue());
        assertThrows(NotFoundException.class, () -> cacheService.delete(KEY));
    }

    @Test
    void clearAllCache() {
        cacheService.clearAllCache();
        assertEquals(Set.of(), cacheService.getKeys(KEY));
    }
}