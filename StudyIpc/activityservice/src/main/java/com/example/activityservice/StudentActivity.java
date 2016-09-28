package com.example.activityservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activityservice.bean.OnStudentAddedListener;
import com.example.activityservice.bean.Student;
import com.example.activityservice.bean.StudentManager;
import com.example.activityservice.service.StudentService;

import java.util.List;

public class StudentActivity extends AppCompatActivity {
    public static final String TAG= Student.class.getSimpleName();
    public static final int MESSAGE_NEW_BOOK_ARRIVED=1;
    private StudentManager studentManager;
    private ServiceConnection serviceConnection;
    private TextView studentInfo;

    private OnStudentAddedListener onStudentAddedListener = new OnStudentAddedListener.Stub() {
        @Override
        public void onNewStudentAdded(Student student) throws RemoteException {
            handler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, student).sendToTarget();
        }
    };



    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {

            switch (message.what) {
                case MESSAGE_NEW_BOOK_ARRIVED:
                    Log.e(TAG, "新同学: " + message.obj);
                    new StudentListAsyncTask().execute();
                    break;
                default:
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        studentInfo= (TextView) findViewById(R.id.student_info);
        initConnection();

        bindMyService();

    }

    private void initConnection() {
        serviceConnection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Toast.makeText(getApplicationContext(),"链接成功",Toast.LENGTH_SHORT).show();
                studentManager=StudentManager.Stub.asInterface(iBinder);
                try {
                    studentManager.registerListener(onStudentAddedListener);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Toast.makeText(getApplicationContext(),"断开成功",Toast.LENGTH_SHORT).show();
                try {
                    studentManager.unregisterListener(onStudentAddedListener);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        };
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        studentManager=null;
    }

    private void bindMyService() {
        Intent intent=new Intent(this, StudentService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);

    }

    public void gosearch(View view) {
      new StudentListAsyncTask().execute();
    }


    class StudentListAsyncTask extends AsyncTask<Void,Void,List<Student>>{

        @Override
        protected List<Student> doInBackground(Void... voids) {
            List<Student> list=null ;

            try {
                list=studentManager.getStudents();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(List<Student> students) {
            super.onPostExecute(students);
            String str="";
            for (int i=0;i<students.size();i++){
                str += students.get(i).toString() + "\n";
            }
            studentInfo.setText(str);
        }
    }
}
