package com.example.serviceexample;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.serviceexample.aidls.IMyAidlInterface;
import com.example.serviceexample.services.MyService3;

public class AidlActivity extends AppCompatActivity {
    public static final String TAG = AidlActivity.class.getSimpleName();

    TextView txtInfo;
    private ServiceConnection connection;
    //申明接口
    private IMyAidlInterface iMyAidlInterface;
    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        txtInfo = (TextView) findViewById(R.id.infoTxt);

        //实例化connection
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                //由IMyAidlInterface.java我们知道asInterface返回IMyAidlInterface
                //并且是服务里面onBind返回的IBinder
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
                Log.e(TAG, "链接成功");
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        //启动服务
        bindService(new Intent(this, MyService3.class), connection, BIND_AUTO_CREATE);
    }

    /**
     * 按钮点击事件
     *
     * @param view
     */
    public void add(View view) throws Exception {
        iMyAidlInterface.addMap(2, android.os.Process.myPid() + "");
    }

    /**
     * 按钮点击事件，查询，抛出异常
     *
     * @param view
     * @throws Exception
     */
    public void search(View view) throws Exception {
        sb.delete(0, sb.length());//清空
        sb.append("activity运行的进程编号:\t" + iMyAidlInterface.getValue(2) + "\n");
        sb.append("service运行的进程编号:\t" + iMyAidlInterface.getValue(1));
        txtInfo.setText(sb.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
