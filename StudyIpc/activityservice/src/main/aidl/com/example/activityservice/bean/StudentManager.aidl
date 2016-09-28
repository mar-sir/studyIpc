// StudentManager.aidl
package com.example.activityservice.bean;

// Declare any non-default types here with import statements
import com.example.activityservice.bean.Student;
import com.example.activityservice.bean.OnStudentAddedListener;

interface StudentManager {
     List<Student> getStudents();

     void addBook(in Student student);
     void registerListener(OnStudentAddedListener listener); // 注册接口
     void unregisterListener(OnStudentAddedListener listener); // 解注册接口
}
