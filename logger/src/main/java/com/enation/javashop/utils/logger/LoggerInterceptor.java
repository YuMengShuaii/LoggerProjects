package com.enation.javashop.utils.logger;

/**
 *  网络传输日志接口
 */

public interface LoggerInterceptor {

    void intercept(@LogType.Control int type, String tag, String content);

}
