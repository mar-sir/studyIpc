// IMyAidlInterface.aidl
package com.example.serviceexample.aidls;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    void addMap(int key,String value);
    String getValue(int key);
}
