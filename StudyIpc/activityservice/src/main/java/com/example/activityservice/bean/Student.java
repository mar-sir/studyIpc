package com.example.activityservice.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by huangcl on 2016/9/27.
 */
public class Student implements Parcelable {

    private String name;
    private String address;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.address);
    }

    public Student() {
    }

    protected Student(Parcel in) {
        this.name = in.readString();
        this.address = in.readString();
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
