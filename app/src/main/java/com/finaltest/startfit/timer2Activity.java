package com.finaltest.startfit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class timer2Activity extends AppCompatActivity {


    TextView textView;
    TimerTask timerTask;
    Timer timer = new Timer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        }
    }

    private void startTimerTask()
    {
        stopTimerTask();

        timerTask = new TimerTask()
        {
            int count = 120;

            @Override
            public void run()
            {
                count--;
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
        }
    }
}