package com.example.serviceexample.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.serviceexample.aidls.IMyAidlInterface;

import java.util.HashMap;

/**
 * Created by huangcl on 2017/1/4.
 */

public class MyService3 extends Service {
    private HashMap<Integer, String> hashMap = new HashMap<>();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //根据IMyAidlInterface.java代码我们可以知道IMyAidlInterface.Stub可以提供IBinder对象,
        //只需实现自定义接口的方法即可
        return new IMyAidlInterface.Stub() {
            @Override
            public void addMap(int key, String value) throws RemoteException {
                hashMap.put(key, value);
            }

            @Override
            public String getValue(int key) throws RemoteException {
                return hashMap.get(key);
            }

        };
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //首先存放服务的进程Id,顺便提一提是运行在不同的进程中。。。
        hashMap.put(1, android.os.Process.myPid() + "");
    }
}
