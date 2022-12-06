package com.example.ttlcacheimplimentation.controller;

import com.example.ttlcacheimplimentation.repository.MyCache;
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

import static com.example.ttlcacheimplimentation.controller.ControllerTestData.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MyCache cache;


    @Test
    public void getCacheTest() {
        cache.add(SET_STRING_1);
        ResponseEntity<String> response = restTemplate.exchange("/GET?key=" + GET_STRING, HttpMethod.GET, null, String.class);
        assertEquals(response.getBody(), GET_RESPONCE);
    }

    @Test
    public void getListOfKeysTest() {
        cache.add(SET_STRING_4);
        ResponseEntity<String> response = restTemplate.exchange("/KEYS?key=" + KEYS_STRING, HttpMethod.GET, null, String.class);
        assertEquals(response.getBody(), KEYS_RESPONCE.toString());
    }

    @Test
    public void setObjectTest() {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<String> response = restTemplate.exchange("/SET" , HttpMethod.POST, new HttpEntity<>(COMMAND_LINE, headers), String.class);
        assertEquals(response.getBody(), SET_RESPONCE);
    }

    @Test
    public void deleteObjectTest() {
        ResponseEntity<String> response = restTemplate.exchange("/DEL?key=" + DELETE_STRING, HttpMethod.DELETE, null, String.class);
        assertEquals(response.getBody(), DELETE_RESPONCE);
    }

    @Test
    public void autoDeleteCache() throws InterruptedException {
        cache.add(SET_STRING_5);
        Thread.sleep(11_000);
        ResponseEntity<String> response = restTemplate.exchange("/GET?key=" + GET_STRING_5, HttpMethod.GET, null, String.class);
        assertEquals(response.getBody(), null);
    }
}