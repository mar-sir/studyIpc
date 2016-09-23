package com.example.activityservice.service;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Designer on 2016/9/22.
 */
public class ServiceA extends Service {
    public static final String TAG=ServiceA.class.getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText( ServiceA.this, TAG+"-->onCreate()", Toast.LENGTH_SHORT).show();
        Log.e(TAG,TAG+"-->onCreate()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return new BinderOne();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText( ServiceA.this, TAG+"-->onStartCommand()", Toast.LENGTH_SHORT).show();
        Log.e(TAG,TAG+"-->onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText( ServiceA.this, TAG+"-->onDestroy()", Toast.LENGTH_SHORT).show();
        Log.e(TAG,TAG+"-->onDestroy()");
    }

    public  class BinderOne extends Binder{
       public void doSomeThing(){
           Toast.makeText( ServiceA.this, "模拟耗時操作", Toast.LENGTH_SHORT).show();
           Log.e(TAG,TAG+"-->模拟耗時操作");
           try {
               Thread.sleep(5000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           Toast.makeText( ServiceA.this, "耗時任務完成", Toast.LENGTH_SHORT).show();
           Log.e(TAG,TAG+"-->耗時任務完成");
       }
    }
}
