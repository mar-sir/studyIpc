package com.example.activityservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.activityservice.service.ServiceA;

public class MainActivity extends AppCompatActivity {
private ServiceConnection  connection;
    private ServiceA.BinderOne binder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initConnection();

        Intent intent=new Intent(this,ServiceA.class);
        bindService(intent,connection,BIND_AUTO_CREATE);

    }

    private void initConnection() {
        connection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
             binder= (ServiceA.BinderOne) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }

    public void downloadAction(View view) {
        //下載
       binder.doSomeThing();
    }

    public void goMessenger(View view) {
        startActivity(new Intent(this,MessengerActivity.class));
    }


    public void goAidl(View view){
      startActivity(new Intent(this,AidlActivity.class));
    }
}
