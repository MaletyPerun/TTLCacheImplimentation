package com.example.ttlcacheimplimentation.repository;

import com.example.ttlcacheimplimentation.model.KeyTimeObject;
import com.example.ttlcacheimplimentation.model.TTLObject;
import com.example.ttlcacheimplimentation.util.Util;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class MyCache {

    private static final long TTL = 20_000;        // 20 sec
    private static final long PERIOD_TIME = 1_000; // 1 sec

    private final ReadWriteLock rwlock = new ReentrantReadWriteLock();

    private final PriorityQueue<KeyTimeObject> keyTimeQueue = new PriorityQueue<>((o1, o2) -> (int) (o1.timeToLive - o2.timeToLive));
    // TODO: 06/12/2022 использовать BlockingQueue при работе с многопоточной очередью
    private final CashStore store;

    public MyCache() {
        this.store = new CashStore();
    }

    public String get(String key) {
        Lock readLock = rwlock.readLock();
        readLock.lock();
        String object;
        try {
            object = store.get(key);
        } finally {
            readLock.unlock();
        }
        return object;
        // TODO: 08/12/2022 обработка на null
    }

    public void add(String strLine) {
        TTLObject ttlObject = Util.createTtlObjcet(strLine);
        KeyTimeObject keyTimeObject = new KeyTimeObject(ttlObject.getKey(), ttlObject.getTimeStamp());
        Lock writeLock = rwlock.writeLock();
        writeLock.lock();
        try {
            keyTimeQueue.add(keyTimeObject);
            store.add(ttlObject);
        } finally {
            writeLock.unlock();
        }

    }

    public Set<String> getKeys(String key) {
        Lock readLock = rwlock.readLock();
        readLock.lock();
        Set<String> set;
        try {
            set = store.getKeys(key);
        } finally {
            readLock.unlock();
        }
        return set;
        // TODO: 06/12/2022 обработка пустого списка set
    }

    public String delete(String key) {
        Lock writeLock = rwlock.writeLock();
        String object;
        writeLock.lock();
        try {
            object = store.delete(key);
        } finally {
            writeLock.unlock();
        }
        return object;
        // TODO: 08/12/2022 обработка на null 
    }


    @Scheduled(fixedDelay = PERIOD_TIME)
    private void autoEvict() {
        // TODO: 08/12/2022 обработка исключения на пустой объект
        if (!keyTimeQueue.isEmpty() &&
                keyTimeQueue.peek().timeToLive <= Util.getTimeStamp(TTL)) {
            KeyTimeObject keyTimeObject = keyTimeQueue.poll();
            store.delete(keyTimeObject.getCacheKey());
        }
    }
}
