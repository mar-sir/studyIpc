package com.example.serviceexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.serviceexample.services.MyService1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void start(View view) {
        Intent intent = new Intent(this, MyService1.class);
        startService(intent);
    }

    public void stop(View view) {
        Intent intent = new Intent(this, MyService1.class);
        stopService(intent);
    }

    public void bind(View view) {

    }

    public void unbind(View view) {

    }
}
