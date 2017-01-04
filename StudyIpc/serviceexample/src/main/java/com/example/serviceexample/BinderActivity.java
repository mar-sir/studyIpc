package com.example.serviceexample;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.serviceexample.services.MyService2;

public class BinderActivity extends AppCompatActivity {

    //申明启动服务所需接口实例
    private ServiceConnection connection;
    public static final String TAG = BinderActivity.class.getSimpleName();
    //申明自定义的Binder对象
    private MyService2.MyBinder myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);

        //实例化
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.e(TAG, "服务已连接，可以开始工作。");
                if (iBinder instanceof MyService2.MyBinder) {//判断是否是自定义Binder类型
                    myBinder = (MyService2.MyBinder) iBinder;
                } else {
                    Log.e(TAG, "但检测到类型不匹配");
                }
            }

            //链接断开回调
            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };

        //启动服务
        bindService(new Intent(this, MyService2.class), connection, BIND_AUTO_CREATE);
    }

    /**
     * 按钮点击事件
     * 调用自定义Binder方法
     *
     * @param view
     */
    public void doThings(View view) {
        if (myBinder != null) myBinder.doSomeThing(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
