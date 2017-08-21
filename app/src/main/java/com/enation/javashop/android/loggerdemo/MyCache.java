package com.enation.javashop.android.loggerdemo;

import com.enation.javashop.utils.logger.Cache;
import com.enation.javashop.utils.logger.LogType;

/**
 * Created by LDD on 17/8/4.
 */

public class MyCache implements Cache {

    @Override
    public void writeToLocal(@LogType.Control int type, String tag, String content) {

    }

    @Override
    public void clearLocal() {

    }
}
