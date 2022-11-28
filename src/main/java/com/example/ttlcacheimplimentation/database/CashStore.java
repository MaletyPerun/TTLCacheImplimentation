package com.example.ttlcacheimplimentation.database;

import com.example.ttlcacheimplimentation.model.TTLObject;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CashStore {
    private ConcurrentMap<String, TTLObject> cacheMap;

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
        Collection<TTLObject> valuesOfCacheMap = cacheMap.values();
        Set<String> mathes = new HashSet<>();
        for (TTLObject x : valuesOfCacheMap) {
            String[] strMessage = x.getObject().split(" ");
            for (String str : strMessage) {
                if (str.contains(key))
                    mathes.add(str);
            }
        }
        return !mathes.isEmpty()? mathes : null;
    }

    public String delete(String key) {
//        TTLObject deletedObj = cacheMap.remove(key);
        return cacheMap.remove(key) != null ? key : null;
    }
}
