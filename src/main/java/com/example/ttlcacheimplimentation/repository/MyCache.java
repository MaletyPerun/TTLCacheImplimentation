package com.example.ttlcacheimplimentation.repository;

import com.example.ttlcacheimplimentation.model.KeyTimeObject;
import com.example.ttlcacheimplimentation.model.TTLObject;
import com.example.ttlcacheimplimentation.util.Util;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class MyCache {

    private static final long TTL = 20_000;        // 20 sec
    private static final long PERIOD_TIME = 1_000; // 1 sec

    private boolean cacheIsEvict = false;

    private final PriorityQueue<KeyTimeObject> keyTimeQueue = new PriorityQueue<>((o1, o2) -> (int) (o1.timeToLive - o2.timeToLive));
    // TODO: 06/12/2022 использовать BlockingQueue при работе с многопоточной очередью
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
        // TODO: 06/12/2022 обработка пустого списка set
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


    // TODO: 06/12/2022 использовать ReentrantReadWriteLock при работе с многопоточкой и синхронностью: использовать пустышку для синхронности
    private void autoEvict() {
        new Thread(() -> {
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
        }).start();

        // TODO: 06/12/2022 старый подход, можно использовать инструмент Spring: @Scheduled
    }
}
