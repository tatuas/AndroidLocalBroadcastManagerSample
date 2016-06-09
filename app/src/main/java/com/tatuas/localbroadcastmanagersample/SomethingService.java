package com.tatuas.localbroadcastmanagersample;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class SomethingService extends IntentService {

    public static final String FILTER_NAME = "SomethingServiceFilter";

    public SomethingService() {
        super("SomethingService");
    }

    @NonNull
    public static Intent createIntent(@NonNull final Context context) {
        return new Intent(context, SomethingService.class);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Log.v(Property.LOG_TAG, "start:" + System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(5L);
            Log.v(Property.LOG_TAG, "stop:" + System.currentTimeMillis());
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(new Intent(FILTER_NAME));
            Log.v(Property.LOG_TAG, "sent:" + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
            Log.v(Property.LOG_TAG, "exception:" + TextUtils.join(" ", e.getStackTrace()));
        }
    }
}
