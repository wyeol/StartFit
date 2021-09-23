package com.finaltest.startfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.finaltest.startfit.calender.calenderfag;
import com.finaltest.startfit.video.videofag;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;

    private com.finaltest.startfit.calender.calenderfag calenderfag;
    private com.finaltest.startfit.video.videofag videofag;
    private boardfag boardfag;
    private tradefag tradefag;
    private mypagefag mypagefag;

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

/*            mFirebaseAuth = FirebaseAuth.getInstance();

            Button btn_logout = findViewById(R.id.btn_logout);
            btn_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFirebaseAuth.signOut();
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);

                    finish();
                }
            });
        //mFirebaseAuth.getCurrentUser().delete()

 */

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
                    case R.id.action_bulletinboard:
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
        boardfag = new boardfag();
        tradefag = new tradefag();
        mypagefag = new mypagefag();
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
}