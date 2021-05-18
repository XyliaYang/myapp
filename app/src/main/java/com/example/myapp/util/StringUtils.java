package com.example.myapp.util;

public class StringUtils {
    public  static boolean isEmpty(String msg){
        if(msg==null || msg.length()<=0){
            return true;
        }else{
            return false;
        }
    }
}
