package com.os.operando.sample;

import android.app.Application;

import com.os.operando.meteor.ComponentType;
import com.os.operando.meteor.Meteor;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Meteor.init(this, SlackPostIntentService.createComponentName(this), ComponentType.Service);
    }
}
