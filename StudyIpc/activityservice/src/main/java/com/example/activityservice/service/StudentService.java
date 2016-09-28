package com.example.activityservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.example.activityservice.bean.OnStudentAddedListener;
import com.example.activityservice.bean.Student;
import com.example.activityservice.bean.StudentManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class StudentService extends Service {

    public static final String TAG= Student.class.getSimpleName();
    //多读少些的迸发
    private CopyOnWriteArrayList<Student> students=new CopyOnWriteArrayList<>();
    //远程回调接口
    private RemoteCallbackList<OnStudentAddedListener> mListenerList = new RemoteCallbackList<>();

    //支持高迸发
    private AtomicBoolean isDestroyed = new AtomicBoolean(false);

    private int num=0;

    public StudentService() {
    }



    //
    private Binder binder=new StudentManager.Stub() {
        @Override
        public List<Student> getStudents() throws RemoteException {
            return students;
        }

        @Override
        public void addBook(Student student) throws RemoteException {
            students.add(student);

        }

        @Override
        public void registerListener(OnStudentAddedListener listener) throws RemoteException {
            mListenerList.register(listener);
            int num = mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            Log.e(TAG, TAG+"---->报道完成, 注册接口数: " + num);
        }

        @Override
        public void unregisterListener(OnStudentAddedListener listener) throws RemoteException {
            mListenerList.unregister(listener);
            int num=mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            Log.e(TAG, TAG+"---->毕业完成, 注册接口数: " + num);
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        students.add(new Student("wang er","火星"));
        students.add(new Student("小明","地球"));

        new Thread( new ServiceWorker()).start();
    }


    class ServiceWorker implements Runnable{
        @Override
        public void run() {

            while (!isDestroyed.get()){
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {

                }
                num++;
                if (num==10){
                    isDestroyed.set(true);
                }
                Message message=Message.obtain();
                handler.sendMessage(message);
            }
        }
    }

    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            int id = 1 + students.size();
            Student student = new Student("新同学","地球"+id);
            onNewStudentArrived(student);
            return false;
        }
    });

    private void onNewStudentArrived(Student student) {
        students.add(student);
        Log.e(TAG, TAG+"---->发送通知的数量: " + students.size());
        int num = mListenerList.beginBroadcast();
        for (int i = 0; i < num; ++i) {
            OnStudentAddedListener listener = mListenerList.getBroadcastItem(i);
            Log.e(TAG, TAG+"---->发送通知: " + listener.toString());
            try {
                listener.onNewStudentAdded(student);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        mListenerList.finishBroadcast();

    }


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
