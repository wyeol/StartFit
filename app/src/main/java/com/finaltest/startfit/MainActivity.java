package com.finaltest.startfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.finaltest.startfit.calender.calenderfag;
import com.finaltest.startfit.fragment.HomeFragment;
import com.finaltest.startfit.fragment.HomeFragment2;
import com.finaltest.startfit.fragment.UserInfoFragment;
import com.finaltest.startfit.login.RegisterActivity;
import com.finaltest.startfit.video.videofag;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends BasicActivity {

    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;

    private com.finaltest.startfit.calender.calenderfag calenderfag;
    private com.finaltest.startfit.video.videofag videofag;
    private HomeFragment boardfag;
    private HomeFragment2 tradefag;
    private UserInfoFragment mypagefag;
    private static final String TAG = "MainActivity";

    private FirebaseAuth mFirebaseAuth;

    private void init(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser == null) {
            myStartActivity(RegisterActivity.class);
        } else {
            DocumentReference documentReference = FirebaseFirestore.getInstance().collection("users").document(firebaseUser.getUid());
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                                myStartActivity(MemberInitActivity.class);
                            }
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });

            calenderfag calenderfag = new calenderfag();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, calenderfag)
                    .commit();

            BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_calendar:
                            calenderfag calenderfag = new calenderfag();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, calenderfag)
                                    .commit();
                            return true;
                        case R.id.action_video:
                            videofag videofag = new videofag();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, videofag)
                                    .commit();
                            return true;
                        case R.id.action_mypage:
                            UserInfoFragment userInfoFragment = new UserInfoFragment();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, userInfoFragment)
                                    .commit();
                            return true;
                        case R.id.home:
                            HomeFragment homeFragment = new HomeFragment();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, homeFragment)
                                    .commit();
                            return true;
                        case R.id.action_trade:
                            HomeFragment2 homeFragment2 = new HomeFragment2();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, homeFragment2)
                                    .commit();
                            return true;
                    }
                    return false;
                }
            });
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }


    @Override
    protected void onPause(){
        super.onPause();
    }


    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivityForResult(intent, 1);
    }
}