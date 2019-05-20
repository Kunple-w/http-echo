package com.goinghugh.utils;

import java.util.UUID;

/**
 * trace log 工具类
 *
 * @author yongxu wang
 * @date 2019-05-20 15:21
 **/
public class TraceLogUtils {
    public static String genTraceId() {
        return UUID.randomUUID().toString();
    }
}
