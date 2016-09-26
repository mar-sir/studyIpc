package com.example.activityservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Designer on 2016/9/22.
 */
public class MessengerService extends Service {
    public static final String TAG = MessengerService.class.getSimpleName();
    public static final int CLIENT_TO_SERVER = 1;
    private static final int SERVER_TO_CLIENT = 2;


    private Messenger clientMessenger;
    private Messenger serverMessenger = new Messenger(new ServerHandler());


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, TAG+"-->onBind()");
        return serverMessenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, TAG+"-->onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,TAG+"-->onDestroy()");
        super.onDestroy();
    }

    class ServerHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Log.e(TAG, TAG + "thread name: " + Thread.currentThread().getName());
            switch (msg.what) {
                case CLIENT_TO_SERVER:
                    Log.e(TAG, "recevie msg from client");
                    clientMessenger = msg.replyTo;
                    //service发送消息给client
                    Message toClientMsg = Message.obtain(null, SERVER_TO_CLIENT);
                    try {
                        Log.e(TAG, "server begin send msg to client");
                        clientMessenger.send(toClientMsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case SERVER_TO_CLIENT:
                    Log.e(TAG, "receive msg from server");
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }
}
