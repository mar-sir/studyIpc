package com.example.designer.studyipc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.designer.studyipc.R;
import com.example.designer.studyipc.views.PaintViewTwo;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondViewFragment extends Fragment {


    PaintViewTwo viewTwo;
    public SecondViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_second_view, container, false);
        viewTwo= (PaintViewTwo) view.findViewById(R.id.viewTow);
        new Thread(viewTwo).start();
        return view;
    }

}
