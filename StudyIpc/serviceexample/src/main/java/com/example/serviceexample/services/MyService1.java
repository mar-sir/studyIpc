package com.example.serviceexample.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by huangcl on 2017/1/3.
 */

public class MyService1 extends Service {
    public static final String TAG = MyService1.class.getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, TAG + "-->onCreate()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, TAG + "-->onBind()");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, TAG + "-->onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, TAG + "-->onDestroy()");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, TAG + "-->onUnbind()");
        return super.onUnbind(intent);
    }
}

