package com.example.ttlcacheimplimentation.repository;

import com.example.ttlcacheimplimentation.dto.TTLObjectDto;
import com.example.ttlcacheimplimentation.model.KeyTimeObject;
import com.example.ttlcacheimplimentation.model.TTLObject;
import com.example.ttlcacheimplimentation.util.TimeUtil;
import com.example.ttlcacheimplimentation.util.TTLObjectUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.example.ttlcacheimplimentation.util.ValidationUtil.checkContent;
import static com.example.ttlcacheimplimentation.util.ValidationUtil.checkNotNull;

@Component
@EnableScheduling
public class MyCache {

    private static final long TTL = 2_000;        // 2 sec
    private static final long PERIOD_TIME = 1_000; // 1 sec

    private final ReadWriteLock rwlock = new ReentrantReadWriteLock();

    private final BlockingQueue<KeyTimeObject> keyTimeQueue = new LinkedBlockingQueue<>();
    private final CashStore store;

    public MyCache() {
        this.store = new CashStore();
    }

    public TTLObjectDto get(String key) {
        Lock readLock = rwlock.readLock();
        readLock.lock();
        TTLObject object;
        try {
            object = store.get(key);
        } finally {
            readLock.unlock();
        }
        checkNotNull(object, key);
        return TTLObjectUtil.createNewObjectDTO(object);
    }

    public void add(String strLine) {
        String[] stringArrayOfCommandLine = strLine.split(" ");
        checkContent(stringArrayOfCommandLine);

        String key = strLine.split(" ")[0];
        int lenOfKey = key.length();
        String obj = strLine.substring(lenOfKey + 1).trim();

        TTLObject ttlObject = new TTLObject(obj, TimeUtil.getTimeStamp());
        KeyTimeObject keyTimeObject = new KeyTimeObject(key, ttlObject.getTimeStamp());

        Lock writeLock = rwlock.writeLock();
        writeLock.lock();
        try {
            keyTimeQueue.add(keyTimeObject);
            store.add(key, ttlObject);
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
        return checkNotNull(set, key);
    }

    public void delete(String key) {
        Lock writeLock = rwlock.writeLock();
        TTLObject object;
        writeLock.lock();
        try {
            object = store.delete(key);
        } finally {
            writeLock.unlock();
        }
        checkNotNull(object, key);
    }


    @Scheduled(fixedDelay = PERIOD_TIME)
    private void autoEvict() {
        if (!keyTimeQueue.isEmpty() &&
                keyTimeQueue.peek().getTimeToLive() <= TimeUtil.getTimeStamp(TTL)) {
            KeyTimeObject keyTimeObject = keyTimeQueue.poll();
            store.delete(keyTimeObject.getCacheKey());
        }
    }

    public void clearAllCache(){
        Lock writeLock = rwlock.writeLock();
        writeLock.lock();
        try {
            keyTimeQueue.clear();
            store.clear();
        } finally {
            writeLock.unlock();
        }
    }
}
