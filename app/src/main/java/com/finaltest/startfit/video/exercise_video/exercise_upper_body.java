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

public class exercise_upper_body extends AppCompatActivity {

    private AdView mAdview; //애드뷰 변수 선언
    
    @Override
    protected void onCreate(Bundle savedInstanceState) { //액티비티의 초기화 작업을 수행합니다.
        super.onCreate(savedInstanceState); //상위 클래스의 onCreate를 호출하여 오버라이드된 메소드를 처리합니다.
        setContentView(R.layout.activity_exercise_upper_body); //전체 레이아웃 인플레이션을 수행합니다.


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
                if(position == 0) { // 상체 스트레칭 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "Xs-oEIPQPI8");
                    startActivity(intent);
                } else if(position == 1) { // 벤치 프레스 운동 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "0DsXTSHo3lU");
                    startActivity(intent);
                } else if(position == 2) { // 체스트 프레스 운동 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "ppPQgmgpafM");
                    startActivity(intent);
                } else if(position == 3) { // 데드리프트 운동 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "EBjYQeeBI-0");
                    startActivity(intent);
                } else if(position == 4) { // 랫풀 다운 운동 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "a_aJqngFeHA");
                    startActivity(intent);
                } else if(position == 5) { // 바벨 로우 운동 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "EEqGCoTuYfQ");
                    startActivity(intent);
                } else if(position == 6) { // 밀리터리 프레스 운동 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "lpc1P_zj3XI");
                    startActivity(intent);
                } else if(position == 7) { // 사이드 레터럴 레이즈 운동 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "YdhHnZxcpgY");
                    startActivity(intent);
                } else if(position == 8) { // 덤벨 컬 운동 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "3t03ehGkH-Q");
                    startActivity(intent);
                }
            }
        });
    }
}