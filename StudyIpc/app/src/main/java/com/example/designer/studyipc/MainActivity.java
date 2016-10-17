package com.example.designer.studyipc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goCoustomerView(View view) {
        startActivity(new Intent(this, CoustomerViewActivity.class));
    }

    public void goView1(View view) {
        startActivity(new Intent(this, CoustomerView2Activity.class));
    }
}
