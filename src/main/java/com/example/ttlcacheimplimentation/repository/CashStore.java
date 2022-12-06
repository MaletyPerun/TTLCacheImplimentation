package com.example.ttlcacheimplimentation.repository;

import com.example.ttlcacheimplimentation.model.TTLObject;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CashStore {
    private final ConcurrentMap<String, TTLObject> cacheMap;

    public CashStore() {
        this.cacheMap = new ConcurrentHashMap<>();
    }

    public String get(String key) {
        return cacheMap.containsKey(key) ? cacheMap.get(key).getObject() : null;
    }

    public TTLObject add(TTLObject ttlObject) {
        return cacheMap.put(ttlObject.getKey(), ttlObject);
    }

    public Set<String> getKeys(String key) {
        Set<String> matches = new HashSet<>();
        // TODO: 06/12/2022 вернуть сразу список по паттерну Case sensitive
        Collection<String> keysOfCacheMap = cacheMap.keySet();
        for (String x : keysOfCacheMap) {
                if (x.contains(key))
                    matches.add(x);
        }
        return matches;
    }

    public String delete(String key) {
//        TTLObject deletedObj = cacheMap.remove(key);
        return cacheMap.remove(key) != null ? key : null;
        // TODO: 06/12/2022 исключить возврат null
    }
}
