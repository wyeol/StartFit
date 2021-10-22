package com.finaltest.startfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.finaltest.startfit.calender.calenderfag;
import com.finaltest.startfit.fragment.HomeFragment;
import com.finaltest.startfit.fragment.UserInfoFragment;
import com.finaltest.startfit.login.RegisterActivity;
import com.finaltest.startfit.video.videofag;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;

    private com.finaltest.startfit.calender.calenderfag calenderfag;
    private com.finaltest.startfit.video.videofag videofag;
    private HomeFragment boardfag;
    private tradefag tradefag;
    private UserInfoFragment mypagefag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            startSignUpActivity();
        }


        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()) {
                    case R.id.action_calendar:
                        setFrag(0);
                        break;
                    case R.id.action_video:
                        setFrag(1);
                        break;
                    case R.id.home:
                        setFrag(2);
                        break;
                    case R.id.action_trade:
                        setFrag(3);
                        break;
                    case R.id.action_mypage:
                        setFrag(4);
                        break;

                }
                return true;
            }
        });
        calenderfag = new calenderfag();
        videofag = new videofag();
        boardfag = new HomeFragment();
        tradefag = new tradefag();
        mypagefag = new UserInfoFragment();
        setFrag(0); // 첫 프래그먼트 화면에 캘린더 화면을 띄움

    }

    // 프래그먼트 교체가 일어나는 실행문
    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, calenderfag);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, videofag);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, boardfag);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame, tradefag);
                ft.commit();
                break;
            case 4:
                ft.replace(R.id.main_frame, mypagefag);
                ft.commit();
                break;

        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }



    private void startSignUpActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}