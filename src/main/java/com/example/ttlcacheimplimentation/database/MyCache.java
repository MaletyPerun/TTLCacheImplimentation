package com.example.ttlcacheimplimentation.database;

import com.example.ttlcacheimplimentation.model.KeyTimeObject;
import com.example.ttlcacheimplimentation.model.TTLObject;
import com.example.ttlcacheimplimentation.util.Util;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Set;

@Component
public class MyCache {

    private static final long TTL = 20_000;        // 20 sec
    private static final long PERIOD_TIME = 1_000; // 1 sec

    private boolean cacheIsEvict = false;

    private PriorityQueue<KeyTimeObject> keyTimeQueue = new PriorityQueue<>(new Comparator<KeyTimeObject>() {
        @Override
        public int compare(KeyTimeObject o1, KeyTimeObject o2) {
            return (int) (o1.timeToLive - o2.timeToLive);
        }
    });

    private CashStore store;

    public MyCache() {
        this.store = new CashStore();
    }

    public String get(String key) {
        return store.get(key);
    }

    public void add(String strLine) {
        start();
        TTLObject ttlObject = Util.createTtlObjcet(strLine);
        KeyTimeObject keyTimeObject = new KeyTimeObject(ttlObject.getKey(), ttlObject.getTimeStamp());
        keyTimeQueue.add(keyTimeObject);
        store.add(ttlObject);
    }

    public Set<String> getKeys(String key) {
        return store.getKeys(key);
    }

    public String delete(String key) {
        return store.delete(key);
    }

    public synchronized void start() {
        if (!cacheIsEvict) {
            this.cacheIsEvict = true;
            autoEvict();
        }
    }

    private void autoEvict() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (cacheIsEvict) {
                    synchronized (keyTimeQueue) {
                        if (!keyTimeQueue.isEmpty() &&
                                keyTimeQueue.peek().timeToLive <= Util.getTimeStamp(TTL)) {
                            KeyTimeObject keyTimeObject = keyTimeQueue.poll();
                            store.delete(keyTimeObject.getCacheKey());
                        }
                    }
                    try {
                        Thread.sleep(PERIOD_TIME);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}
