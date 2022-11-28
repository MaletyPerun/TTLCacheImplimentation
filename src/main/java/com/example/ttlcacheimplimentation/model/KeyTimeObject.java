package com.example.ttlcacheimplimentation.model;


import lombok.Getter;
@Getter public class KeyTimeObject {
    public long timeToLive;
    public String cacheKey;

    public KeyTimeObject(String cacheKey, long timeToLive) {
        this.cacheKey = cacheKey;
        this.timeToLive = timeToLive;
    }
}
