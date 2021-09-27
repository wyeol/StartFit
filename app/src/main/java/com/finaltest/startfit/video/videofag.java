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
import com.finaltest.startfit.video.exercise_video.exercise_upper_body;

public class videofag extends Fragment{

    private View view;
    private Button mbt;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.video_fag, container, false);


        mbt = view.findViewById(R.id.exercise1);

        mbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), exercise_upper_body.class);
                startActivity(intent);
            }
        });

        return view;
    }


}
