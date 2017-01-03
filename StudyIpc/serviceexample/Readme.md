### Service（服务）
#### 服务的生命周期
![](https://github.com/mar-sir/studyIpc/blob/master/StudyIpc/serviceexample/src/main/java/images/step1.gif?raw=true)
#### 服务的分类
* 远程服务---->运行在不同的进程中的服务。
 通过对android:process=":service"属性指定不同进程。
* 本地服务---->运行在同一进程中的服务。

按调用方式分类。

* startService启动,即使启动它的应用组建销毁了，服务也会存在。不进行通信。停止服务使用stopService(不管你调用了多少次startService)；

* bindService调用启动，当启动它的组建销毁，服务也会跟着销毁。可以进行通信。停止服务使用unbindService；
 
* startService、bindService 同时启动，停止服务应同时使用stopService与unbindService
下面来写一个服务，在它们的生命周期回调方法中分别打印日志。代码如下:

        public class MyService1 extends Service {
            public static final String TAG = MyService1.class.getSimpleName();
        
        
            @Override
            public void onCreate() {
                super.onCreate();
                Log.e(TAG, TAG + "-->onCreate()");
            }
        
            @Nullable
            @Override
            public IBinder onBind(Intent intent) {
                Log.e(TAG, TAG + "-->onBind()");
                return null;
            }
        
            @Override
            public int onStartCommand(Intent intent, int flags, int startId) {
                Log.e(TAG, TAG + "-->onStartCommand()");
                return super.onStartCommand(intent, flags, startId);
            }
        
            @Override
            public void onDestroy() {
                super.onDestroy();
                Log.e(TAG, TAG + "-->onDestroy()");
            }
        
            @Override
            public boolean onUnbind(Intent intent) {
                Log.e(TAG, TAG + "-->onUnbind()");
                return super.onUnbind(intent);
            }
        }
    
别忘了在清单配置文件里配置。<service android:name=".services.MyService1"></service>接着我们来看一下startService，startService、bindService
启动的生命周期调用。其中MainActivity的布局文件代码：

    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/activity_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center"
        android:orientation="vertical"
        tools:context="com.example.serviceexample.MainActivity">
    
        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="10dp"
            android:onClick="start"
            android:text="start启动服务" />
    
        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="10dp"
            android:onClick="stop"
            android:text="stop停止服务" />
    
        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="10dp"
            android:onClick="bind"
            android:text="bind启动服务" />
    
        <Button
            android:onClick="unbind"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="10dp"
            android:text="unbind停止服务" />
    
    
    </LinearLayout>


#### startService
    
    public void start(View view) {
        Intent intent = new Intent(this, MyService1.class);
        startService(intent);
    }

    public void stop(View view) {
        Intent intent = new Intent(this, MyService1.class);
        stopService(intent);
    }
点击start启动服务，点击stop停止服务。就看一下它的生命周期。onCreate()-->onStartCommand()-->onDestroy();

![](https://github.com/mar-sir/studyIpc/blob/master/StudyIpc/serviceexample/src/main/java/images/step2.png?raw=true)

多次点击start启动服务，会是什么现象？
![](https://github.com/mar-sir/studyIpc/blob/master/StudyIpc/serviceexample/src/main/java/images/step3.png?raw=true)
你会发现就调用了一次onCreate(),这是由于onCreate()方法只会在Service第一次被创建的时候调用，如果当前Service已经被创建过了，
不管怎样调用startService()方法，onCreate()方法都不会再执行,而onStartCommand()不是。