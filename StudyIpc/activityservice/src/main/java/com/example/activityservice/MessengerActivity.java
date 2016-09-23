package com.example.activityservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.activityservice.service.MessengerService;

public class MessengerActivity extends AppCompatActivity {
    public static final String TAG = MessengerActivity.class.getSimpleName();

    private ServiceConnection connection = new MessengerConnection();
    private boolean mBound=false;

    private Messenger messenger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
    }

    public void bindAction(View view) {
        Intent intent=new Intent(this, MessengerService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    public void sendMsg(View view) {
        if (mBound) {
            Message message = Message.obtain();
            message.replyTo=messenger;
            message.what = MessengerService.CLIENT_TO_SERVER;
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void unBindAction(View view) {
        if (mBound) unbindService(connection);
    }


    class MessengerConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, TAG + "-->MessengerConnection connected");
            messenger = new Messenger(service);

            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.w(TAG, TAG+"-->MessengerConnection onServiceDisconnected");
            mBound = false;
        }
    }



}
