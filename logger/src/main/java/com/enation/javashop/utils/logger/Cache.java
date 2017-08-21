package com.enation.javashop.utils.logger;

/**
 * 日志本地缓存接口
 */

public interface Cache {

    /**
     * 写入本地
     * @param type       类型
     * @param tag        标记
     * @param content    内容
     */
    void writeToLocal(@LogType.Control int type, String tag, String content);

    /**
     * 清空日志缓存
     */
    void clearLocal();

}
