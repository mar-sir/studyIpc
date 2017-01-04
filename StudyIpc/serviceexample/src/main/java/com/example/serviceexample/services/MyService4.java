package com.example.serviceexample.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.serviceexample.MessengerActivity;

/**
 * Created by huangcl on 2017/1/4.
 */

public class MyService4 extends Service {
    private Messenger cilentMessenger;
    //需要传一个Handler或IBinder,此处传handler
    private Messenger serverMessenger = new Messenger(new MessengerHandler());


    public static final int RECEVIE = 1;//接受标识
    public static final int SEND = 2;//发送标识

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return serverMessenger.getBinder();
    }


    class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //处理activity发过来的消息
                case RECEVIE:
                    //实例化给客户端的信使
                    Bundle bundle = (Bundle) msg.obj;
                    Log.e(MessengerActivity.TAG, "服务端接收到:\t" + bundle.getString("key"));
                    cilentMessenger = msg.replyTo;
                    Message message = Message.obtain(null, SEND);//相当于message.what=SEND;
                    //这里并没有跨进程，所以可以
                    message.obj = "傻逼，你妹啊！";
                    try {
                        Log.e(MessengerActivity.TAG, "服务端开始发送消息");
                        cilentMessenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case SEND:
                    Log.e(MessengerActivity.TAG, "客户端收到:\t" + msg.obj);
                    break;
                default:
                    break;
            }
        }
    }
}
