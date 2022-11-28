package com.example.ttlcacheimplimentation.controller;

import com.example.ttlcacheimplimentation.model.CommandLine;
import java.util.List;

public class ControllerTestData {
    public static final String GET_STRING = "prime";
    public static final String GET_STRING_5 = "test5";
    public static final String SET_STRING_1 = "prime Price-list of new cars";
    public static final String SET_STRING_2 = "cold Cold prince of Persia";
    public static final String SET_STRING_4 = "test4 test4 book for all world";
    public static final String SET_STRING_5 = "test5 test5 book for all world";

    public static final String KEYS_STRING = "boo";
    public static final String DELETE_STRING = "test";

    public static final String DELETE_RESPONCE = "Object with key: " + DELETE_STRING + " is deleted";
    public static final String GET_RESPONCE = "Price-list of new cars";

    public static final List<String> KEYS_RESPONCE = List.of("book");

    public static final CommandLine COMMAND_LINE = new CommandLine("testCount", SET_STRING_2);
    public static final String SET_RESPONCE = "set cold Cold prince of Persia";
}
