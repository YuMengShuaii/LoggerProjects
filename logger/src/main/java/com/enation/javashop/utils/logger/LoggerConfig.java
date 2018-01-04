package com.enation.javashop.utils.logger;

import java.util.ArrayList;

/**
 * Created by LDD on 2018/1/4.
 */

public interface LoggerConfig {

        LoggerConfig diskCache();
        LoggerConfig diskCache(Cache cache);
        LoggerConfig setTag(String tag);
        LoggerConfig addInterceptor(LoggerInterceptor interceptor);
        LoggerConfig addInterceptors(ArrayList<LoggerInterceptor> interceptors);
        Logger build();
}
