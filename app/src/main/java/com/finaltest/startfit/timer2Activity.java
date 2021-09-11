package com.finaltest.startfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class timer2Activity extends AppCompatActivity {


    TextView textView;
    TimerTask timerTask;
    Timer timer = new Timer();
    int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer2);

        textView = findViewById(R.id.textView2);
    }

    @Override
    protected void onDestroy()
    {
        timer.cancel();
        super.onDestroy();
    }
    public void clickHandler(View view)
    {
        switch(view.getId())
        {
            case R.id.btnStart:
                startTimerTask();
                break;
            case R.id.btnReset :
                stopTimerTask();
                break;
            case R.id.plus60 :
                addmin();
                break;
            case R.id.minus60 :
                minusmin();
                break;
            case R.id.plus30 :
                addsec();
                break;
            case R.id.minus30 :
                minussec();
                break;

        }
    }
    private void addmin(){
        count += 60;
        textView.setText(count+"초");
    }
    private void addsec(){
        count += 30;
        textView.setText(count+"초");
    }
    private void minusmin(){
        count -= 60;
        textView.setText(count+"초");
    }
    private void minussec(){
        count -= 30;
        textView.setText(count+"초");
    }
    Ringtone rt;
    private void startTimerTask()
    {
        stopTimerTask();

        timerTask = new TimerTask()
        {


            @Override
            public void run()
            {
                count--;
                if(count==0){
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(500);
                    textView.setText("");
                    timerTask.cancel();
                    timerTask = null;

                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                    rt = RingtoneManager.getRingtone(getApplicationContext(),notification);
                    rt.play();
                    count=0;
                }
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(count + " 초");
                    }
                });
            }
        };
        timer.schedule(timerTask,0 ,1000);
    }

    private void stopTimerTask()
    {
        if(timerTask != null)
        {
            textView.setText("");
            timerTask.cancel();
            timerTask = null;
            count=0;
            rt.stop();
        }
    }
}