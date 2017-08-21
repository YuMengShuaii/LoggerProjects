package com.enation.javashop.utils.logger;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by LDD on 17/8/4.
 */

public class LogType {
    public static final int ERROR = 0;
    public static final int DEBUG = 1;
    public static final int INFO = 2;

    @IntDef({ERROR, DEBUG,INFO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Control {

    }
}
