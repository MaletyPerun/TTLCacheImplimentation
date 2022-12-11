package com.example.ttlcacheimplimentation.repository;

import com.example.ttlcacheimplimentation.model.TTLObject;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class CacheStore {
    private final ConcurrentMap<String, TTLObject> cacheMap;

    public CacheStore() {
        this.cacheMap = new ConcurrentHashMap<>();
    }

    public TTLObject get(String key) {
        return cacheMap.get(key);
    }

    public TTLObject add(String key, TTLObject ttlObject) {
        return cacheMap.put(key, ttlObject);
    }

    public Set<String> getKeys(String key) {
        Set<String> matches = new HashSet<>();
        Collection<String> keysOfCacheMap = cacheMap.keySet();
        Pattern pattern = Pattern.compile(".*" + key + ".*", Pattern.CASE_INSENSITIVE);
        for (String x : keysOfCacheMap) {
            Matcher matcher = pattern.matcher(x);
            if (matcher.matches())
                matches.add(x);
        }
        return matches;
    }

    public TTLObject delete(String key) {
        return cacheMap.remove(key);
    }

    public void clear() {
        cacheMap.clear();
    }
}
