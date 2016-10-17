package com.example.designer.studyipc;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.designer.studyipc.adapter.ViewAdapter;
import com.example.designer.studyipc.fragments.CanvasOneFragment;
import com.example.designer.studyipc.fragments.CanvasTwoFragment;
import com.example.designer.studyipc.fragments.CoustomerOneFragment;
import com.example.designer.studyipc.fragments.FirstViewFragment;
import com.example.designer.studyipc.fragments.FiveFragment;
import com.example.designer.studyipc.fragments.FontFragmentOne;
import com.example.designer.studyipc.fragments.FourFragment;
import com.example.designer.studyipc.fragments.MulitiCricleFragment;
import com.example.designer.studyipc.fragments.SecondViewFragment;
import com.example.designer.studyipc.fragments.ShapeFourFragment;
import com.example.designer.studyipc.fragments.ShapeFragmentTwo;
import com.example.designer.studyipc.fragments.ShapeOneFragment;
import com.example.designer.studyipc.fragments.ShapeThreeFragment;
import com.example.designer.studyipc.fragments.ThreeFragment;
import com.example.designer.studyipc.fragments.WaveFragment;

import java.util.ArrayList;

public class CoustomerView2Activity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private ViewAdapter viewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coustomer_view2);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        initViews();
    }

    private void initViews() {
        fragments.add(new CoustomerOneFragment());

        titles.add("自定义View1");
     /*   titles.add("自定义View2");
        titles.add("自定义View3");
        titles.add("自定义View4");
        titles.add("自定义View5");
        titles.add("自定义View6");
        titles.add("自定义View7");
        titles.add("自定义View8");
        titles.add("自定义View9");
        titles.add("自定义View10");
        titles.add("自定义View11");
        titles.add("自定义View12");
        titles.add("自定义View13");
        titles.add("自定义View14");*/
        viewAdapter = new ViewAdapter(getSupportFragmentManager(), titles, fragments);
        viewPager.setAdapter(viewAdapter);
        tabLayout.setupWithViewPager(viewPager);



    }
}
