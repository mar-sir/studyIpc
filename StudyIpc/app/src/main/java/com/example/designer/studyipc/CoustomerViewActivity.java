package com.example.designer.studyipc;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.designer.studyipc.adapter.ViewAdapter;
import com.example.designer.studyipc.fragments.FirstViewFragment;
import com.example.designer.studyipc.fragments.FiveFragment;
import com.example.designer.studyipc.fragments.FontFragmentOne;
import com.example.designer.studyipc.fragments.FourFragment;
import com.example.designer.studyipc.fragments.SecondViewFragment;
import com.example.designer.studyipc.fragments.ThreeFragment;

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
        fragments.add(new SecondViewFragment());
        fragments.add(new ThreeFragment());
        fragments.add(new FourFragment());
        fragments.add(new FiveFragment());
        fragments.add(new FontFragmentOne());
        titles.add("自定义View1");
        titles.add("自定义View2");
        titles.add("自定义View3");
        titles.add("自定义View4");
        titles.add("自定义View5");
        titles.add("自定义View6");
        viewAdapter = new ViewAdapter(getSupportFragmentManager(), titles, fragments);
        viewPager.setAdapter(viewAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
