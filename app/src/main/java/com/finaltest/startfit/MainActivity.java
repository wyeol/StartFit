package com.finaltest.startfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mFirebaseAuth = FirebaseAuth.getInstance();

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
    }
}