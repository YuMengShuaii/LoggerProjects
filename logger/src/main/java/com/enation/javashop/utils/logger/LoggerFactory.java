package com.enation.javashop.utils.logger;

import android.content.Context;

/**
 * Created by LDD on 2018/1/4.
 */

public class LoggerFactory {

    private static LogImpl LOGGER;

    public static LoggerConfig create(Context context){
        if (LOGGER == null){
            LOGGER = new LogImpl();
            LOGGER.init(context);
        }
        return LOGGER;
    }

    public static Logger getLogger(){
        return LOGGER;
    }
}
