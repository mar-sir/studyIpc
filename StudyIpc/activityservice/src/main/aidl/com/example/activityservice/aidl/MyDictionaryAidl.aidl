// MyDictionaryAidl.aidl
package com.example.activityservice.aidl;

// Declare any non-default types here with import statements

interface MyDictionaryAidl {
   void add(String chinese,String english);
   String search(String keyword);
}
