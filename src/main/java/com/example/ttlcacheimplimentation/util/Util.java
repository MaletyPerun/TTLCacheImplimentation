package com.example.ttlcacheimplimentation.util;

import com.example.ttlcacheimplimentation.model.TTLObject;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Util {

    public static long getTimeStamp(){
        return System.currentTimeMillis();
    }

    public static long getTimeStamp(long timeToLive) {
        return getTimeStamp() - timeToLive;
    }

    public static TTLObject createTtlObjcet(String strLine) {
        String[] strArray = strLine.split(" ");
        String key = strArray[0];
        int lenOfKey = key.length();
        String obj = strLine.substring(lenOfKey + 1).trim();
        return new TTLObject(key, obj, getTimeStamp());
    }
}
