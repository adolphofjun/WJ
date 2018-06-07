package com.pluten.utils;

public class MyException extends RuntimeException {
    private String MSG = "自定义异常";
    public MyException(String msg){
        super(msg);
    }
}
