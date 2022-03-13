package com.example.four_pic;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

import com.example.four_pic.utils.SharedPreferencesHelper;

public class WinActivity extends AppCompatActivity {
    SharedPreferencesHelper shared = new SharedPreferencesHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_win);
        startTimer();

    }
    private void startTimer(){
        Object object;
        object = new CountDownTimer(3000, 1000) {

            @Override
            public void onFinish() {
                finish();
                Intent intent = new Intent(WinActivity.this, MenuActivity.class);
                startActivity(intent);
            }
            @Override
            public void onTick(long l) {

            }

        }.start();
    }
}

