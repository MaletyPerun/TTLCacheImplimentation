package com.example.ttlcacheimplimentation.controller;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Unmapper {

    public static String unmap(String responseBody) {
        return responseBody.replaceAll("\"", "").replaceAll(",", ", ");
    }
}
