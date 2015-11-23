package com.os.operando.sample;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.os.operando.meteor.Meteor;
import com.os.operando.meteoroid.Meteoroid;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SlackPostIntentService extends IntentService {

    public static ComponentName createComponentName(Context context) {
        return new ComponentName(context.getPackageName(), SlackPostIntentService.class.getName());
    }

    public SlackPostIntentService() {
        super(SlackPostIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("SlackPostIntentService", "onHandleIntent");
        File cacheDirectory = getCacheDir();
        File file = new File(cacheDirectory.getAbsolutePath() + ".jpg");
        try {
            if (file.createNewFile()) {
                Log.d("SlackPostIntentService", "Fail create file.");
            }
            FileOutputStream outputStream = null;
            try {
                Bitmap bitmap = Meteor.getScreenshotBitmap(intent);
                outputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            } finally {
                outputStream.close();
            }
            new Meteoroid.Builder()
                    .channels("#general")
                    .title("test")
                    .initialComment("Post")
                    .uploadFile(file)
                    .token("your Slack API token")
                    .build()
                    .post(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("SlackPostIntentService", "Fail create file.");
        }
    }
}
