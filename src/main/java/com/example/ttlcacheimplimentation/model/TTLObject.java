package com.example.ttlcacheimplimentation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TTLObject {

    private String value;
    private long timeStamp;
}
