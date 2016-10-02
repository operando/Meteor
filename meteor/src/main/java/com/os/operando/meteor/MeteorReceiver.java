package com.os.operando.meteor;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.Window;

public class MeteorReceiver extends BroadcastReceiver {

    private static final String component_name = "component_name";
    private static final String component_type = "component_type";

    public static Intent createIntent(Context context) {
        return new Intent(context, MeteorReceiver.class);
    }

    public static PendingIntent createPendingIntent(Context context, int requestCode, int flags,
                                                    @NonNull ComponentName componentName, ComponentType componentType) {
        Intent intent = createIntent(context);
        intent.putExtra(component_name, componentName);
        intent.putExtra(component_type, componentType);
        return PendingIntent.getBroadcast(context, requestCode, intent, flags);
    }

    public MeteorReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Window w = WindowStack.peek();
        if (w == null) {
            // TODO Error callback
            return;
        }
        Bitmap b = ViewUtils.getDecorViewBitmap(w);
        String uuid = ScreenshotBitmapCache.putBitmap(b);
        ComponentName componentName = intent.getParcelableExtra(component_name);
        Intent i = new Intent();
        i.putExtra(Meteor.Screenshot_key, uuid);
        i.setComponent(componentName);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        switch (((ComponentType) intent.getSerializableExtra(component_type))) {
            case Activity:
                context.startActivity(i);
                break;
            case Service:
                context.startService(i);
                break;
            case BroadcastReceiver:
                context.sendBroadcast(i);
                break;
        }
    }
}
