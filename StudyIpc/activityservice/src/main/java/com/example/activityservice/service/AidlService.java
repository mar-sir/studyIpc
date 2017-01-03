package com.example.activityservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.activityservice.aidl.MyDictionaryAidl;

import java.util.HashMap;

public class AidlService extends Service {
    private HashMap<String, String> myDictionary = new HashMap<>();
    public static final String TAG = AidlService.class.getSimpleName();


    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, TAG + android.os.Process.myPid() + "");
        return new MyDictionaryAidl.Stub() {
            @Override
            public void add(String chinese, String english) throws RemoteException {
                myDictionary.put(chinese, english);
            }

            @Override
            public String search(String keyword) throws RemoteException {
                return myDictionary.get(keyword);
            }
        };
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myDictionary.put("id", android.os.Process.myPid() + "");
        Log.e(TAG, TAG + "--->" + "onCreate()");
        Log.e(TAG, TAG + android.os.Process.myPid() + "");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, TAG + "--->" + "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        Log.e(TAG, TAG + "--->" + "onDestroy()");
        super.onDestroy();
    }
}
