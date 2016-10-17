package com.example.jxlexample;

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

    public void doPrint(View view) {
        Intent intent = new Intent(this, PrintActivity.class);
        intent.putExtra("name", "打印文稿");
        startActivity(intent);
    }
}
