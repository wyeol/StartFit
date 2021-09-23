package com.finaltest.startfit.video.exercise_video;

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

public class exercise_full_body extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_full_body);

        ListView listView = findViewById(R.id.exercise_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String sItem = ((TextView)view).getText().toString(); //해당 항목의 텍스트를 저장합니다.
                Toast.makeText(getApplicationContext(), sItem, Toast.LENGTH_SHORT).show(); //해당 항목의 텍스트를 띄워줍니다.
                if(position == 0) { // 맨몸 스트레칭 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "wtKuJGn1M_g");
                    startActivity(intent);
                } else if(position == 1) { // 푸쉬 업 운동 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "aoH7qNedO8k");
                    startActivity(intent);
                } else if(position == 2) { // 풀 업 운동 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "nWhS28U6bCY");
                    startActivity(intent);
                } else if(position == 3) { // 친 업 운동 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "YMAti6DjOiI");
                    startActivity(intent);
                } else if(position == 4) { // 크런치 운동 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "KqnFav4Edvw");
                    startActivity(intent);
                } else if(position == 5) { // 플랭크 운동 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "B--6YfhmBGc");
                    startActivity(intent);
                } else if(position == 6) { // 맨몸 스쿼트 운동 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "9Sl5tI4_J-0");
                    startActivity(intent);
                } else if(position == 7) { // 카프 레이즈 운동 리스트를 선택한 경우
                    Intent intent = new Intent(getApplicationContext(), VideoView.class);
                    intent.putExtra("영상", "OHp56zqYn6U");
                    startActivity(intent);
                }
            }
        });
    }
}