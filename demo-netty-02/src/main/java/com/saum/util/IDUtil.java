package com.saum.util;

import java.util.UUID;

/**
 * @Author saum
 * @Description:
 */
public class IDUtil {
    public static String randomId(){
        return UUID.randomUUID().toString().split("-")[0];
    }
}
