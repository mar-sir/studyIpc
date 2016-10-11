package com.example.designer.studyipc;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.designer.studyipc.adapter.ViewAdapter;
import com.example.designer.studyipc.fragments.FirstViewFragment;

import java.util.ArrayList;

public class CoustomerViewActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private ViewAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coustomer_view);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        initViews();
    }

    private void initViews() {
        fragments.add(new FirstViewFragment());
        titles.add("自定义View1");
        viewAdapter = new ViewAdapter(getSupportFragmentManager(), titles, fragments);
        viewPager.setAdapter(viewAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
