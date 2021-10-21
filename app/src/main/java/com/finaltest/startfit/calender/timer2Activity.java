package com.finaltest.startfit.calender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.finaltest.startfit.R;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class timer2Activity extends AppCompatActivity {


    Button TimerStartBtn; // START 버튼 변수
    TextView textView; // 타이머 시간이 보이는 텍스트뷰
    TimerTask timerTask; // 타이머 기능
    Timer timer = new Timer(); //타이머 설정
    int count = 0; // 타이머 시간
    private boolean mTimerRunning; // 타이머가 진행되고 있는지 확인하는 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer2);

        textView = findViewById(R.id.textView2);
        TimerStartBtn = findViewById(R.id.btnStart);

    }

    @Override
    protected void onDestroy()
    {
        timer.cancel();
        super.onDestroy();
    }
    public void clickHandler(View view)  // 버튼을 클릭했을 때 일어날 이벤트를 처리하는 메소드
    {
        switch(view.getId())
        {
            case R.id.btnStart: // 타이머 스타트
                if(mTimerRunning) {
                    pauseTimerTask();
                } else {
                    if(count <=0) {
                        Toast.makeText(getApplicationContext(), "시간을 입력해주세요", Toast.LENGTH_SHORT).show();
                        textView.setText("");
                        break;
                    }
                    startTimerTask();
                }
                break;
            case R.id.btnReset : // 타이머 초기화
                stopTimerTask();
                break;
            case R.id.plus60 : // 타이머 60초 추가 버튼을 클릭했을 때
                addmin();
                break;
            case R.id.minus60 : // 타이머 60초 감소 버튼을 클릭했을 때
                if(count > 60) {
                    minusmin();
                } else {
                    Toast.makeText(getApplicationContext(), "시간이 부족합니다.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.plus30 : // 타이머 30초 추가 버튼을 클릭했을 때
                addsec();
                break;
            case R.id.minus30 : // 타이머 30초 감소 버튼을 클릭했을 때
                if(count > 30) {
                    minussec();
                } else {
                    Toast.makeText(getApplicationContext(), "시간이 부족합니다.", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
    private void addmin(){ // 타이머 60초 추가 처리 메소드
        count += 60;
        textView.setText(count+"");
    }
    private void addsec(){ // 타이머 30초 추가 처리 메소드
        count += 30;
        textView.setText(count+"");
    }
    private void minusmin(){ // 타이머 60초 감소 처리 메소드
        count -= 60;
        textView.setText(count+"");
    }
    private void minussec(){ // 타이머 30초 감소 처리 메소드
        count -= 30;
        textView.setText(count+"");
    }

    private void startTimerTask() // 타이머가 시작했을 때 처리 메소드
    {

        mTimerRunning = true; //타이머가 흘러감을 나타냅니다.
        TimerStartBtn.setText("PAUSE"); // 타이머가 흘러갈 때 시작 버튼을 일시정지로 바꿉니다.
        TimerStartBtn.setBackgroundResource(R.drawable.button_background_pause);
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                count--;
                if(count==0){
                    timerTask.cancel();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTimerRunning = false;
                            TimerStartBtn.setText("START"); // 타이머가 흘러갈 때 시작 버튼을 일시정지로 바꿉니다.
                            TimerStartBtn.setBackgroundResource(R.drawable.button2_background);
                        }
                    });
                }

                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(count + "");
                    }
                });
            }
        };
        timer.schedule(timerTask,0 ,1000);
    }

    private void stopTimerTask() //타이머를 초기화 했을 때 처리 메소드
    {
        if(timerTask != null)
        {
            timerTask.cancel();
            mTimerRunning = false;
            TimerStartBtn.setText("START");
            TimerStartBtn.setBackgroundResource(R.drawable.button2_background);
        }
        textView.setText("");
        count=0;
    }


    private void pauseTimerTask() {
        timerTask.cancel();
        mTimerRunning = false;
        TimerStartBtn.setText("START");
        TimerStartBtn.setBackgroundResource(R.drawable.button2_background);
    }
}