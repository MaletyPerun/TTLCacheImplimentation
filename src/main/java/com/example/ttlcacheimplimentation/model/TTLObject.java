package com.example.ttlcacheimplimentation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TTLObject {

    private String value;
    private long timeStamp;
}
