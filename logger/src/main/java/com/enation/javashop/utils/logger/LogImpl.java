package com.enation.javashop.utils.logger;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by LDD on 2018/1/4.
 */

class LogImpl implements LoggerConfig,Logger {

    /**
     * 是否开启Logger
     */
    private boolean isOpenLogger = true;

    /**
     * 默认LoggerTag
     */
    private String LOGGER_TAG = "JavaShop";

    /**
     * 本地缓存类
     */
    private Cache cache;

    /**
     * 上下文
     */
    private Context context;

    /**
     * 日志拦截器列表
     */
    private ArrayList<LoggerInterceptor> interceptors;

    /**
     * 防止外部实例化该类
     */

    public LoggerConfig init(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public LoggerConfig diskCache() {
        cache = new DiskCache(context);
        return this;
    }

    @Override
    public LoggerConfig diskCache(Cache cache) {
        this.cache = cache;
        return this;
    }

    @Override
    public void off() {
        isOpenLogger = false;
    }

    @Override
    public void removeInterceptor(LoggerInterceptor interceptor) {
        if (interceptors != null){
            interceptors.remove(interceptor);
        }
    }

    @Override
    public void clearInterceptor() {
        if (interceptors!=null){
            interceptors.clear();
        }
    }

    @Override
    public LoggerConfig setTag(String tag) {
        this.LOGGER_TAG = tag;
        return this;
    }

    @Override
    public LoggerConfig addInterceptor(LoggerInterceptor interceptor) {
        if (interceptors == null){
            interceptors = new ArrayList<>();
        }
        interceptors.add(interceptor);
        return this;
    }

    @Override
    public LoggerConfig addInterceptors(ArrayList<LoggerInterceptor> interceptors) {
        if (interceptors == null){
            interceptors = new ArrayList<>();
        }
        interceptors.addAll(interceptors);
        return this;
    }

    @Override
    public Logger build() {
        return this;
    }

    /**
     * Error日志打印
     *
     * @param tag     标记
     * @param message 日志信息
     */
    @Override
    public void e(String tag, String message) {
        if (isOpenLogger) {
            if (cache != null) {
                cache.writeToLocal(LogType.ERROR,tag,message);
            }
            interceptor(LogType.ERROR,tag,message);
            Log.e(tag, message);
        }
    }

    /**
     * Error日志打印 默认Tag
     *
     * @param message 日志信息
     */
    @Override
    public void e(String message) {
        e(LOGGER_TAG, message);
    }

    /**
     * Debug日志打印
     *
     * @param tag     标记
     * @param message 日志信息
     */
    @Override
    public void d(String tag, String message) {
        if (isOpenLogger) {
            if (cache != null){
                cache.writeToLocal(LogType.DEBUG,tag,message);
            }
            interceptor(LogType.DEBUG,tag,message);
            Log.d(tag, message);
        }
    }

    /**
     * Debug默认Tag
     *
     * @param message 日志信息
     */
    @Override
    public void d(String message) {
        d(LOGGER_TAG, message);
    }

    /**
     * INFO日志打印
     *
     * @param tag     标记
     * @param message 日志信息
     */
    @Override
    public void i(String tag, String message) {
        if (isOpenLogger) {
            if (cache != null){
                cache.writeToLocal(LogType.INFO,tag,message);
            }
            interceptor(LogType.INFO,tag,message);
            Log.i(tag, message);
        }
    }

    /**
     * INFO日志打印 默认Tag
     *
     * @param message 日志信息
     */
    @Override
    public void i(String message) {
        i(LOGGER_TAG, message);
    }

    @Override
    public void clearLocal() {
        cache.clearLocal();
    }

    private void interceptor(@LogType.Control int type, String tag, String content){
        if (interceptors != null && interceptors.size() > 0){
            for (LoggerInterceptor interceptor : interceptors) {
                interceptor.intercept(type,tag,content);
            }
        }
    }
}
