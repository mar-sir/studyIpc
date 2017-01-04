package com.example.serviceexample;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.serviceexample.services.MyService4;

public class MessengerActivity extends AppCompatActivity {
    private ServiceConnection connection;
    public static final String TAG = MessengerActivity.class.getSimpleName();

    private Messenger cilentMessenger;//声明

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                //需要传一个Handler或IBinder,此处传iBinder
                cilentMessenger = new Messenger(iBinder);
                Log.e(TAG, "信使服务链接成功");

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        //启动服务
        bindService(new Intent(this, MyService4.class), connection, BIND_AUTO_CREATE);
    }


    /**
     * 发送按钮点击事件
     *
     * @param view
     */
    public void send(View view) {
        Log.e(TAG, "信使开始发送消息");
        Message message = Message.obtain(null, MyService4.RECEVIE);//message.what = MyService4.RECEVIE,表示服务的接受，也就是activity的发送，看着有点怪
        message.replyTo = cilentMessenger;
        //message.obj = "傻逼，在吗？";//这里有个坑，
        //当用Messenger在两个进程之间传递Message时，Message的obj不能设置为设置为non-Parcelable的对象，
        //比如在跨进程的情形下，Message的obj设置为了一个String对象，那么在Messenger执行send(Message)方法时就会报错，不信你自己试。
        //用以下代码代替
        Bundle bundle = new Bundle();
        bundle.putString("key", "傻逼，你在吗?");
        message.obj = bundle;
        try {
            cilentMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
