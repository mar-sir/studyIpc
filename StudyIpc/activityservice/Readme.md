### 与服务的通信
#### Binder
在自定义服务的时候，Service本身是抽象类，所以自定义的服务必须要实现Service的抽象方法，这里实现了onBind()方法，你会发现这个方法返回一个IBinder对象。我们就用这返回
的IBinder对象做些事情。

那我就给它返回一个自定义Binder,同时做一些事情。代码如下：

        public class MyService2 extends Service {
            @Nullable
            @Override
            public IBinder onBind(Intent intent) {
                //返回自定义Binder
                return new MyBinder();
            }
        
            /**
             * 自定义Binder
             */
            public static class MyBinder extends Binder {
        
                public void doSomeThing(Context context) {
                    try {
                        Thread.sleep(4000);
                        Log.e(BinderActivity.TAG, "我睡了4秒钟");
                        Toast.makeText(context, "我睡了4秒钟", Toast.LENGTH_LONG).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
代码非常简单，其他生命周期方法我就不写了。那个IBinder对象返回到哪里了，我发现在bindService启动服务的时候传了一个ServiceConnection接口的实例对象，它的实现方法里有IBinder
参数。那我们不妨试试。看Activity里面的代码：

    public class BinderActivity extends AppCompatActivity {
    
        //申明启动服务所需接口实例
        private ServiceConnection connection;
        public static final String TAG = BinderActivity.class.getSimpleName();
        //申明自定义的Binder对象
        private MyService2.MyBinder myBinder;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_binder);
    
            //实例化
            connection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    Log.e(TAG, "服务已连接，可以开始工作。");
                    if (iBinder instanceof MyService2.MyBinder) {//判断是否是自定义Binder类型
                        myBinder = (MyService2.MyBinder) iBinder;
                    } else {
                         Log.e(TAG, "但检测到类型不匹配");
                    }
                }
    
                //链接断开回调
                @Override
                public void onServiceDisconnected(ComponentName componentName) {
    
                }
            };
    
            //启动服务
            bindService(new Intent(this, MyService2.class), connection, BIND_AUTO_CREATE);
        }
    
        /**
         * 按钮点击事件，就是在布局文件中写的按钮点击事件，布局文件代码就不贴了
         * 调用自定义Binder方法
         *
         * @param view
         */
        public void doThings(View view) {
            if (myBinder != null) myBinder.doSomeThing(this);
        }
    
        @Override
        protected void onDestroy() {
            super.onDestroy();
            unbindService(connection);
        }
    }
代码都有解释，也比较简单，下面我们配置一下服务<service android:name=".services.MyService2"></service>，运行程序。点击按钮运行,等四秒后运行结果如下：


这样我们就通过Binder完成了与服务的简单通信。那我们配置服务的时候稍微加点东西就像

         <service android:name=".services.MyService2"
                    android:process=":myService2"></service>
这样配置，相当于是远程服务，不在同一个进程，我们再次运行程序,发现：


这样的通信方式不能满足与远程服务的通信，那么我给你介绍几个能和远程服务通信的方式。
### aidl  
Android Interface definition language的缩写,它是一种android内部进程通信接口的描述语言，通过它我们可以定义进程间的通信接口。

Android Studio中创建.aidl文件很简单，跟新建Activity差不多，只是选择的是AIDL,不要建错了，一直下一步，然后我们改一下.aidl接口里面的方法，如下：
    
        // IMyAidlInterface.aidl
        package com.example.serviceexample.aidls;
        
        // Declare any non-default types here with import statements
        
        interface IMyAidlInterface {
            void addMap(int key,String value);//添加
            String getValue(int key);//查询
        }
然后rebuild一下项目，你会发现在build/generated/source/debug/下面发现自动生成的代码IMyAidlInterface.java，如图:


我们好奇，看一下IMyAidlInterface.java代码有些啥？

    package com.example.serviceexample.aidls;
    // Declare any non-default types here with import statements
    //申明导入所需包的地方，这没啥用，说不说都一样。
    
    /**
     * IMyAidlInterface接口继承自android.os.IInterface接口，IInterface接口
     * 也就一个方法，你可以自行看看
     */
    public interface IMyAidlInterface extends android.os.IInterface {
        /**
         * Local-side IPC implementation stub class.
         * 本地IPC实现类。继承自Binder,需要实现你自己定义的IMyAidlInterface接口，因为
         * 它自己也是一个抽象类，它能给我们提供一个Binder对象
         */
        public static abstract class Stub extends android.os.Binder implements com.example.serviceexample.aidls.IMyAidlInterface {
            private static final java.lang.String DESCRIPTOR = "com.example.serviceexample.aidls.IMyAidlInterface";
    
            /**
             * Construct the stub at attach it to the interface.
             * 构造方法，并将其绑定IMyAidlInterface接口
             */
            public Stub() {
                this.attachInterface(this, DESCRIPTOR);
            }
    
            /**
             * Cast an IBinder object into an com.example.serviceexample.aidls.IMyAidlInterface interface,
             * generating a proxy if needed.
             * 将IBinder对象强转换到com.example.serviceexample.aidls.IMyAidlInterface接口中，
             *  看需要生成代理。
             */
            public static com.example.serviceexample.aidls.IMyAidlInterface asInterface(android.os.IBinder obj) {
                //如果传的Ibinder对象obj为空，则返回
                if ((obj == null)) {
                    return null;
                }
                //否则根据com.example.serviceexample.aidls.IMyAidlInterface路径得到IInterface对象
                android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
                //如果iin不为空，并且属于自定义的IMyAidlInterface接口，就返回自定义的IMyAidlInterface
                //asInterface()参数结合服务里onBind方法可知
                //obj是com.example.serviceexample.aidls.IMyAidlInterface类型的
                if (((iin != null) && (iin instanceof com.example.serviceexample.aidls.IMyAidlInterface))) {
                    return ((com.example.serviceexample.aidls.IMyAidlInterface) iin);
                }
                //否则返回代理的IInterface，看Proxy类自己实现了自定义的IMyAidlInterface接口。
                //看一下Proxy类
                return new com.example.serviceexample.aidls.IMyAidlInterface.Stub.Proxy(obj);
            }
    
            /**
             * 返回Binder
             *
             * @return
             */
            @Override
            public android.os.IBinder asBinder() {
                return this;
            }
    
            @Override
            public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
                switch (code) {
                    case INTERFACE_TRANSACTION: {
                        reply.writeString(DESCRIPTOR);
                        return true;
                    }
                    case TRANSACTION_addMap: {
                        data.enforceInterface(DESCRIPTOR);
                        int _arg0;
                        _arg0 = data.readInt();
                        java.lang.String _arg1;
                        _arg1 = data.readString();
                        this.addMap(_arg0, _arg1);
                        reply.writeNoException();
                        return true;
                    }
                    case TRANSACTION_getValue: {
                        data.enforceInterface(DESCRIPTOR);
                        int _arg0;
                        _arg0 = data.readInt();
                        java.lang.String _result = this.getValue(_arg0);
                        reply.writeNoException();
                        reply.writeString(_result);
                        return true;
                    }
                }
                return super.onTransact(code, data, reply, flags);
            }
    
            private static class Proxy implements com.example.serviceexample.aidls.IMyAidlInterface {
                private android.os.IBinder mRemote;//定义
    
                Proxy(android.os.IBinder remote) {
                    mRemote = remote;
                }//初始化
    
                @Override
                public android.os.IBinder asBinder() {
                    return mRemote;
                }//返回Binder
    
                public java.lang.String getInterfaceDescriptor() {
                    return DESCRIPTOR;
                }
    
                /**
                 * 实现IMyAidlInterface接口里的addMap方法
                 *
                 * @param key
                 * @param value
                 * @throws android.os.RemoteException
                 */
                @Override
                public void addMap(int key, java.lang.String value) throws android.os.RemoteException {
                    android.os.Parcel _data = android.os.Parcel.obtain();
                    android.os.Parcel _reply = android.os.Parcel.obtain();
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        _data.writeInt(key);
                        _data.writeString(value);
                        mRemote.transact(Stub.TRANSACTION_addMap, _data, _reply, 0);
                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }
                }
    
                /**
                 * 实现IMyAidlInterface接口里的getValue方法
                 *
                 * @param key
                 * @return
                 * @throws android.os.RemoteException
                 */
                @Override
                public java.lang.String getValue(int key) throws android.os.RemoteException {
                    android.os.Parcel _data = android.os.Parcel.obtain();
                    android.os.Parcel _reply = android.os.Parcel.obtain();
                    java.lang.String _result;
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        _data.writeInt(key);
                        mRemote.transact(Stub.TRANSACTION_getValue, _data, _reply, 0);
                        _reply.readException();
                        _result = _reply.readString();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }
                    return _result;
                }
            }
    
            //我理解为标识
            static final int TRANSACTION_addMap = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
            static final int TRANSACTION_getValue = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
        }
    
        public void addMap(int key, java.lang.String value) throws android.os.RemoteException;
    
        public java.lang.String getValue(int key) throws android.os.RemoteException;
    }
至于为解释的onTransact()方法，那是因为现在的我还不太清楚，但是很重要，很重要，很重要[不过有人知道，哈哈](http://www.jianshu.com/p/b260051237fe)感兴趣的可以去看看。我们接着写。
写服务类。

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
看Activity里面的代码。

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
布局代码

    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/activity_aidl"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center_horizontal"
        android:orientation="vertical"
        tools:context="com.example.serviceexample.AidlActivity">
    
        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="10dp"
            android:onClick="add"
            android:text="添加" />
    
        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="10dp"
            android:onClick="search"
            android:text="查找" />
    
        <TextView
            android:id="@+id/infoTxt"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
    
    
    </LinearLayout>
配置服务，并运行程序；
    
    <!--远程服务-->
            <service
                android:name=".services.MyService3"
                android:process=":myService3"></service>
##### 运行结果

可知是运行在不同的进程，并且aidl能解决与远程服务通信的问题，稍微比Binder复杂了点。

### Messenger（信使）
这里就只是简单的贴代码了，用法和解释都在代码里面。


* Activity类

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

* 服务类
    
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

* 配置

        <service
                    android:name=".services.MyService4"
                    android:process=":myService4"></service>
##### 运行结果
