package com.example.ttlcacheimplimentation.controller;

import com.example.ttlcacheimplimentation.dto.TTLObjectDto;
import com.example.ttlcacheimplimentation.service.CacheService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static com.example.ttlcacheimplimentation.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CacheService cacheService;

    @BeforeEach
    void setUp() {
        cacheService.add(KEY_1, VALUE_1);
        cacheService.add(KEY_2, VALUE_2);
    }

    @AfterEach
    void evict() {
        cacheService.clearAllCache();
    }


    @Test
    void getCacheTest() {
        ResponseEntity<TTLObjectDto> response = restTemplate.exchange(GET_URL, HttpMethod.GET, null, TTLObjectDto.class);
        assertEquals(GET_TTL_OBJECT_DTO.getBody(), response.getBody());
    }

    @Test
    void getSetOfKeysTest() {
        ResponseEntity<Set> response = restTemplate.exchange(KEYS_URL, HttpMethod.GET, null, Set.class);
        assertEquals(KEYS_RESPONSE, response.getBody());
    }

    @Test
    void setObjectTest() {
        ResponseEntity<String> response = restTemplate.exchange(SET_URL, HttpMethod.POST, new HttpEntity<>(setTtlObjectDtoData(), new HttpHeaders()), String.class);
        assertEquals(SET_RESPONSE, response.getBody());
    }

    @Test
    void deleteObjectTest() {
        ResponseEntity<String> response = restTemplate.exchange(DELETE_URL, HttpMethod.DELETE, null, String.class);
        assertEquals(DELETE_RESPONSE, response.getBody());
    }

    @Test
    void autoDeleteCache() throws InterruptedException {
        Thread.sleep(5_000);
        ResponseEntity<String> response = restTemplate.exchange(GET_URL, HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}