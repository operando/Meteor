package com.os.operando.sample;

import android.app.Application;

import com.os.operando.meteor.ComponentType;
import com.os.operando.meteor.Meteor;
import com.os.operando.meteor.MeteorActivityLifecycleCallbacks;
import com.os.operando.meteor.MeteorNotification;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Meteor.init(this, SlackPostIntentService.createComponentName(this), ComponentType.Service);
        MeteorNotification.show(this, SlackPostIntentService.createComponentName(this), ComponentType.Service);
        registerActivityLifecycleCallbacks(new MeteorActivityLifecycleCallbacks());
    }
}
