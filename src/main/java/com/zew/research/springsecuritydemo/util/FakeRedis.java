package com.zew.research.springsecuritydemo.util;

import java.util.HashMap;
import java.util.Map;

//模拟的Redis
public class FakeRedis {
    private Map<String,String> cacheMap;
    private static FakeRedis ourInstance = new FakeRedis(

    );

    public static FakeRedis getInstance() {
        return ourInstance;
    }

    private FakeRedis() {
        cacheMap=new HashMap<>();
    }
    public void setKV(String key,String value){
        cacheMap.put(key,value);

    }
    public String getKey(String key){
        return cacheMap.get(key);
    }
}
