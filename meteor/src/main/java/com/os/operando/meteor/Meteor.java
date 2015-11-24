package com.os.operando.meteor;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;

public class Meteor {

    public static final String Screenshot_key = "screenshot_key";

    public static void init(Application application, ComponentName componentName, ComponentType componentType) {
        MeteorNotification.show(application, componentName, componentType);
        application.registerActivityLifecycleCallbacks(new MeteorActivityLifecycleCallbacks());
    }

    @Nullable
    public static Bitmap getScreenshotBitmap(Intent intent) {
        String key = intent.getStringExtra(Screenshot_key);
        if (key == null) {
            return null;
        }
        return ScreenshotBitmapCache.getBitmap(key);
    }
}
