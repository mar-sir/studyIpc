package com.example.activityservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.activityservice.aidl.MyDictionaryAidl;
import com.example.activityservice.service.AidlService;

public class AidlActivity extends AppCompatActivity {
    private ServiceConnection serviceConnection;

    private MyDictionaryAidl myDictionaryAidl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        initConnection();

        startService();
    }

    private void initConnection() {
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                myDictionaryAidl= MyDictionaryAidl.Stub.asInterface(iBinder);
                Toast.makeText(getApplicationContext(),"链接成功",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
    }

    private void startService() {
        Intent intent = new Intent(this, AidlService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    public void add(View view) {
       if (myDictionaryAidl!=null){
           try {
               myDictionaryAidl.add("你好","hello");
           } catch (RemoteException e) {
               e.printStackTrace();
           }
       }else{
           Toast.makeText(getApplicationContext(),"myDictionaryAidl==null",Toast.LENGTH_SHORT).show();
       }
    }

    public void search(View view) {
        if (myDictionaryAidl!=null){
            try {
                String words = myDictionaryAidl.search("你好");
                Toast.makeText(getApplicationContext(),words,Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getApplicationContext(),"myDictionaryAidl==null",Toast.LENGTH_SHORT).show();
        }
    }
}
