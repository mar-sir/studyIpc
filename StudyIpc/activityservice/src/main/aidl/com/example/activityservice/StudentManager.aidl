// StudentManager.aidl
package com.example.activityservice;

// Declare any non-default types here with import statements

interface StudentManager {
     List<Student> etStudents();
     void comeStudent(in Student student);
            void registerListener(IOnNewBookArrivedListener listener); // 注册接口
            void unregisterListener(IOnNewBookArrivedListener listener); // 注册接口
}
