package com.enation.javashop.utils.logger;

/**
 * Created by LDD on 2018/1/4.
 */

public interface Logger {
    void i(String message);
    void i(String tag, String message);
    void d(String message);
    void d(String tag, String message);
    void e(String message);
    void e(String tag, String message);
    void clearLocal();
    void off();
    void removeInterceptor(LoggerInterceptor interceptor);
    void clearInterceptor();
}