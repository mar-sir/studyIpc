package com.example.designer.studyipc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.designer.studyipc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CanvasOneFragment extends Fragment {


    public CanvasOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_canvas_one, container, false);
    }

}
