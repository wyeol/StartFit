package com.finaltest.startfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class exercise_upper_body extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { //액티비티의 초기화 작업을 수행합니다.
        super.onCreate(savedInstanceState); //상위 클래스의 onCreate를 호출하여 오버라이드된 메소드를 처리합니다.
        setContentView(R.layout.activity_exercise_upper_body); //전체 레이아웃 인플레이션을 수행합니다.

        ListView listView = findViewById(R.id.exercise_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String sItem = ((TextView)view).getText().toString(); //해당 항목의 텍스트를 저장합니다.
                Toast.makeText(getApplicationContext(), sItem, Toast.LENGTH_SHORT).show(); //해당 항목의 텍스트를 띄워줍니다.
                if(position == 0) { // 푸쉬업 운동 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "0DsXTSHo3lU");
                    startActivity(intent);
                } else if(position == 1) { // 풀업 운동 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "WWpFmpmyrow");
                    startActivity(intent);
                } else if(position == 2) { // 플랭크 운동 리스트를 선택한 경우
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/Zq8nRY9P_cM")); //암시적 인텐트로 값을 갖고 이동합니다.
                    startActivity(intent); //액티비티 실행
                } else if(position == 3) { // 스쿼트 운동 리스트를 선택한 경우
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/IfJcq4LDXKE")); //암시적 인텐트로 값을 갖고 이동합니다.
                    startActivity(intent); //액티비티 실행
                } else if(position == 4) { // 힙쓰러스트 운동 리스트를 선택한 경우
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/kNv-0UEUb2Q")); //암시적 인텐트로 값을 갖고 이동합니다.
                    startActivity(intent); //액티비티 실행
                } else if(position == 5) { // 카프레이즈 운동 리스트를 선택한 경우
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/UBEYmHEC9PE")); //암시적 인텐트로 값을 갖고 이동합니다.
                    startActivity(intent); //액티비티 실행
                } else if(position == 6) { // 러닝 운동 리스트를 선택한 경우
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/nnmiUT8CXjQ")); //암시적 인텐트로 값을 갖고 이동합니다.
                    startActivity(intent); //액티비티 실행
                } else if(position == 7) { // 전신 7가지 운동 리스트를 선택한 경우
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/zSJYAyoojdw")); //암시적 인텐트로 값을 갖고 이동합니다.
                    startActivity(intent); //액티비티 실행
                }
            }
        });
    }
}