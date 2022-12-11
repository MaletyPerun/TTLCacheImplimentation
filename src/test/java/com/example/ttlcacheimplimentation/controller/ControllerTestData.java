package com.example.ttlcacheimplimentation.controller;

import java.util.List;

public class ControllerTestData {
    public static final String GET_URL = "/GET?key=test1";
    public static final String DELETE_URL = "/DEL?key=test1";
    public static final String KEYS_URL = "/KEYS?key=est";
    public static final String GET_RESPONSE = "Price-list of new cars";
    public static final String SET_STRING_1 = "test1 Price-list of new cars";
    public static final String SET_STRING_2 = "test2 Cold prince of Persia";
    public static final List<String> KEYS_RESPONSE = List.of("test2", "test1");
    public static final String SET_RESPONSE = "set test1 Price-list of new cars";
    public static final String DELETE_KEY = "test1";
    public static final String DELETE_RESPONSE = "Object with key: " + DELETE_KEY + " is deleted";
}
