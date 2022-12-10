package com.example.ttlcacheimplimentation.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TimeUtil {

    public static long getTimeStamp(){
        return System.currentTimeMillis();
    }

    public static long getTimeStamp(long timeToLive) {
        return getTimeStamp() - timeToLive;
    }
}
