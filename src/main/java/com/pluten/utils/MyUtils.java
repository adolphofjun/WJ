package com.pluten.utils;

import java.util.Map;
import java.util.Random;

public class MyUtils {

    /**
     * 检查是否包含必填参数
     * @param map
     * @param key
     */
    public static void checkArgument(Map map, String key){
        if(!map.containsKey(key)||"".equals(map.get(key)))  throw new MyException(Constant.ARGUMENT_EXCEPTION.getExplanation());
    }


    public static int getRandom(int max){
        Random ra =new Random();
        int rs = ra.nextInt(max);
        return  rs;
    }
}
