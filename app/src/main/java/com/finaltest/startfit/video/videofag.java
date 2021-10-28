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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class videofag extends Fragment{

    private View view;
    private Button mbt1, mbt2, mbt3, mbt4;
    private AdView mAdview; //애드뷰 변수 선언


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.video_fag, container, false);


        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

        mAdview = view.findViewById(R.id.adView); //배너광고 레이아웃 가져오기
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);
        AdView adView = new AdView(getActivity());
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("\n" + "ca-app-pub-3940256099942544/6300978111");

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
