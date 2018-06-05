package com.pluten.utils;

import java.util.Map;

public class MapUtils {
    public static String getStringMap(String key,Map map){
        if(map==null) return  "";
        if(key==null) return  "";
        if(!map.containsKey(key)) return  "";
        return  map.get(key)+"";
    }





}
