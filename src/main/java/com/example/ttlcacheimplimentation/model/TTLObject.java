package com.example.ttlcacheimplimentation.model;

import lombok.Getter;

@Getter
public class TTLObject {

    public String key;
    public String object;
    public long timeStamp;

    public TTLObject(String key, String object, long timeStamp) {
        this.key = key;
        this.object = object;
        this.timeStamp = timeStamp;
    }
}
