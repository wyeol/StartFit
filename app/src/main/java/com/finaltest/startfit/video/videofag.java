package com.finaltest.startfit.video;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.finaltest.startfit.R;
import com.finaltest.startfit.video.exercise_video.exercise_full_body;
import com.finaltest.startfit.video.exercise_video.exercise_lower_body;
import com.finaltest.startfit.video.exercise_video.exercise_running;
import com.finaltest.startfit.video.exercise_video.exercise_upper_body;

public class videofag extends Fragment{

    private View view;
    private Button mbt1, mbt2, mbt3, mbt4;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.video_fag, container, false);


        mbt1 = view.findViewById(R.id.exercise1);
        mbt2 = view.findViewById(R.id.exercise2);
        mbt3 = view.findViewById(R.id.exercise3);
        mbt4 = view.findViewById(R.id.exercise4);


        mbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), exercise_upper_body.class);
                startActivity(intent);
            }
        });

        mbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), exercise_lower_body.class);
                startActivity(intent);
            }
        });

        mbt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), exercise_full_body.class);
                startActivity(intent);
            }
        });

        mbt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), exercise_running.class);
                startActivity(intent);
            }
        });




        return view;
    }


}
