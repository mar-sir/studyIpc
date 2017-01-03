package com.example.serviceexample;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.serviceexample.services.MyService1;

public class MainActivity extends AppCompatActivity {
    private ServiceConnection connection;//服务连接
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, TAG +"Thread id: "+ Thread.currentThread().getId());
        //实现连接的回调
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.e(TAG, "onServiceConnected（）+");
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
    }


    public void start(View view) {
        Intent intent = new Intent(this, MyService1.class);
        startService(intent);
    }

    public void stop(View view) {
        Intent intent = new Intent(this, MyService1.class);
        stopService(intent);
    }

    public void bind(View view) {
        Intent intent = new Intent(this, MyService1.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    public void unbind(View view) {
        unbindService(connection);
    }
}
