package com.enation.javashop.utils.logger;

import android.app.Activity;
import android.util.Log;

/**
 * 日志工具
 *
 * @author LDD
 */

public class Logger {

    /**
     * 是否开启Logger
     */
    private static boolean isOpenLogger = false;

    /**
     * 默认LoggerTag
     */
    private static String loggerTag = "JavaShop";

    /**
     * 本地缓存类
     */
    private static Cache cache;

    /**
     * 初始化Logger
     *
     * @param context   上下文
     * @param isToLocal 是否开启本地序列化
     * @param mcache    日志本地化接口
     */
    public static void LoggerInit(Activity context, boolean isToLocal, Cache mcache) {
        isOpenLogger = true;
        if (isToLocal) {
            if (mcache == null) {
                cache = new DiskCache(context);
            } else {
                cache = mcache;
            }
        }
    }

    /**
     * 初始化Logger
     *
     * @param context   上下文
     * @param isToLocal 是否开启本地序列化
     */
    public static void LoggerInit(Activity context, boolean isToLocal) {
        LoggerInit(context, isToLocal, null);
    }


    /**
     * 清除本地日志文件
     */
    public static void clearLocal() {
        cache.clearLocal();
    }

    /**
     * Error日志打印
     *
     * @param tag     标记
     * @param message 日志信息
     */
    public static void e(String tag, String message) {
        if (isOpenLogger) {
            if (cache != null) {
                cache.writeToLocal(LogType.ERROR,tag,message);
            }
            Log.e(tag, message);
        }
    }

    /**
     * Error日志打印 默认Tag
     *
     * @param message 日志信息
     */
    public static void e(String message) {
        e(loggerTag, message);
    }

    /**
     * Debug日志打印
     *
     * @param tag     标记
     * @param message 日志信息
     */
    public static void d(String tag, String message) {
        if (isOpenLogger) {
            if (cache != null){
                cache.writeToLocal(LogType.DEBUG,tag,message);
            }
            Log.d(tag, message);
        }
    }

    /**
     * Debug默认Tag
     *
     * @param message 日志信息
     */
    public static void d(String message) {
        d(loggerTag, message);
    }

    /**
     * INFO日志打印
     *
     * @param tag     标记
     * @param message 日志信息
     */
    public static void i(String tag, String message) {
        if (isOpenLogger) {
            if (cache != null){
                cache.writeToLocal(LogType.INFO,tag,message);
            }
            Log.i(tag, message);
        }
    }

    /**
     * INFO日志打印 默认Tag
     *
     * @param message 日志信息
     */
    public static void i(String message) {
        i(loggerTag, message);
    }
}
