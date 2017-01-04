package com.example.serviceexample.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.serviceexample.BinderActivity;

/**
 * Created by huangcl on 2017/1/4.
 */

public class MyService2 extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //返回自定义Binder
        return new MyBinder();
    }

    /**
     * 自定义Binder
     */
    public static class MyBinder extends Binder {

        public void doSomeThing(Context context) {
            try {
                Thread.sleep(4000);
                Log.e(BinderActivity.TAG, "我睡了4秒钟");
                Toast.makeText(context, "我睡了4秒钟", Toast.LENGTH_LONG).show();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
