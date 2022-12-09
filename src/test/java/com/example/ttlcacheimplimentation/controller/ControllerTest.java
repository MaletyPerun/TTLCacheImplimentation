package com.example.ttlcacheimplimentation.controller;

import com.example.ttlcacheimplimentation.repository.MyCache;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static com.example.ttlcacheimplimentation.controller.ControllerTestData.*;
import static com.example.ttlcacheimplimentation.controller.Unmapper.unmap;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MyCache cache;


    @AfterEach
    void evict(){
        cache.clearAllCache();
    }


    @Test
    void getCacheTest() {
        cache.add(SET_STRING_1);
        ResponseEntity<String> response = restTemplate.exchange(GET_URL, HttpMethod.GET, null, String.class);
        assertEquals("{\"object\":\"" + GET_RESPONSE + "\"}", response.getBody());
    }

    @Test
    void getListOfKeysTest() {
        cache.add(SET_STRING_1);
        cache.add(SET_STRING_2);
        ResponseEntity<String> response = restTemplate.exchange(KEYS_URL, HttpMethod.GET, null, String.class);
        assertEquals(unmap(Objects.requireNonNull(response.getBody())), KEYS_RESPONSE.toString());
    }

    @Test
    void setObjectTest() {
        HttpHeaders headers = new HttpHeaders();
        COMMAND_LINE.setStrLine(SET_STRING_1);
        ResponseEntity<String> response = restTemplate.exchange("/SET", HttpMethod.POST, new HttpEntity<>(COMMAND_LINE, headers), String.class);
        assertEquals(SET_RESPONSE, response.getBody());
    }

    @Test
    void deleteObjectTest() {
        cache.add(SET_STRING_1);
        ResponseEntity<String> response = restTemplate.exchange(DELETE_URL, HttpMethod.DELETE, null, String.class);
        assertEquals(DELETE_RESPONSE, response.getBody());
    }

    @Test
    void autoDeleteCache() throws InterruptedException {
        cache.add(SET_STRING_1);
        Thread.sleep(5_000);
        ResponseEntity<String> response = restTemplate.exchange(GET_URL, HttpMethod.GET, null, String.class);
        assertEquals("Not found keys/object with key: " + DELETE_KEY, response.getBody());
    }
}