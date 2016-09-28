// OnStudentAddedListener.aidl
package com.example.activityservice.bean;
import com.example.activityservice.bean.Student;

interface OnStudentAddedListener {
    void onNewStudentAdded(in Student student);
}