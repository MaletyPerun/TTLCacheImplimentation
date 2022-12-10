package com.example.ttlcacheimplimentation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KeyTimeObject {

    private String cacheKey;
    private long timeToLive;
}
