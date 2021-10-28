package com.finaltest.startfit.video.exercise_video;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.finaltest.startfit.R;
import com.finaltest.startfit.video.VideoView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class exercise_running extends AppCompatActivity {

    private AdView mAdview; //애드뷰 변수 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_running);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

        mAdview = findViewById(R.id.adView); //배너광고 레이아웃 가져오기
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("\n" + "ca-app-pub-3940256099942544/6300978111");


        ListView listView = findViewById(R.id.exercise_choice_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String sItem = ((TextView)view).getText().toString(); //해당 항목의 텍스트를 저장합니다.
                Toast.makeText(getApplicationContext(), sItem, Toast.LENGTH_SHORT).show(); //해당 항목의 텍스트를 띄워줍니다.
                if(position == 0) { // 러닝 스트레칭 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "4P-fUsQ3T-c");
                    startActivity(intent);
                } else if(position == 1) { // 러닝 기초 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "nnmiUT8CXjQ");
                    startActivity(intent);
                } else if(position == 2) { // 러닝 효과 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "GLMMw3CZUxU");
                    startActivity(intent);
                } else if(position == 3) { // 러닝 호흡 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "hL0DTNPp8RE");
                    startActivity(intent);
                }
            }
        });
    }
}