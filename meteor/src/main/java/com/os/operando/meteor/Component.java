package com.os.operando.meteor;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Component {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ACTIVITY, SERVICE, BROADCAST_RECEIVER})
    public @interface Type {
    }

    public static final int NONE = -1;
    public static final int ACTIVITY = 0;
    public static final int SERVICE = 1;
    public static final int BROADCAST_RECEIVER = 2;
}